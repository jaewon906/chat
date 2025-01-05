package com.project.test.chat.security;

import com.project.test.chat.common.TokenGeneration;
import com.project.test.chat.exception.TokenValidationFailedException;
import com.project.test.chat.login.VO.LoginVO;
import com.project.test.chat.mapper.login.LoginMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class LoginFilter extends OncePerRequestFilter {

    @Value("${signature.access-token.key}")
    private String ACCESS_TOKEN_KEY;

    @Value("${signature.refresh-token.key}")
    private String REFRESH_TOKEN_KEY;

    @Value("${access-token-name}")
    private String ACCESS_TOKEN_NAME;

    @Value("${refresh-token-name}")
    private String REFRESH_TOKEN_NAME;
    private final LoginMapper loginMapper;
    private final TokenGeneration tokenGeneration;

    LoginFilter(LoginMapper loginMapper, TokenGeneration tokenGeneration) {
        this.loginMapper = loginMapper;
        this.tokenGeneration = tokenGeneration;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {

        //1. Cookie에 저장된 토큰들을 불러옴
        Cookie[] cookies = req.getCookies();
        String accessToken = "";
        String refreshToken = "";
        if(cookies != null) {
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals(ACCESS_TOKEN_NAME)) {
                    accessToken = cookie.getValue();
                }
                if (cookie.getName().equals(REFRESH_TOKEN_NAME)) {
                    refreshToken = cookie.getValue();
                }
            }
        }

        //2. accessToken 검증. 성공 시 인증 객체 생성
        String[] role = accessTokenValidation(accessToken);
        if(role != null) { // accessToken 검증 성공 시 인증객체 생성
            SecurityContextHolder.getContext().setAuthentication(setAuthentication(role));
            chain.doFilter(req, res);
            return;
        }
        //3. refreshToken 검증. 성공 시 accessToken 생성 후 재검증??
        String issuer = refreshTokenValidation(refreshToken);
        if(issuer != null) { // refreshToken 검증 성공 시 로직 재실행
            LoginVO loginVO = new LoginVO();
            loginVO.setUserId(issuer);
            List<LoginVO> userAuth = loginMapper.getUserAuth(loginVO);
            loginVO.setRoleCd(userAuth.get(0).getRoleCd());
            loginVO.setRoleNm(userAuth.get(0).getRoleNm());
            accessToken = tokenGeneration.generateAccessToken(loginVO).getValue();
            role = accessTokenValidation(accessToken);
            if (role != null) {
                SecurityContextHolder.getContext().setAuthentication(setAuthentication(role));
                res.addCookie(tokenGeneration.generateAccessToken(loginVO));

            }
        }
        //4. 두개의 토큰 검증 실패 시
        chain.doFilter(req, res);
    }

    private String[] accessTokenValidation(String token){
        try{
            if(token == null || token.isEmpty()) throw new TokenValidationFailedException("token is empty");
            Key key = new SecretKeySpec(ACCESS_TOKEN_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            log.info("Login Info::::::::::::::");
            log.info(claims.toString());
            return convertToRoleList(claims.getBody().get("ROLE").toString());
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid Access Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired Access Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported Access Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        } catch (Exception e) {
            log.info("Exception", e);
        }
        return null;
    }
    private String refreshTokenValidation(String token){
        try{
            if(token == null || token.isEmpty()) throw new TokenValidationFailedException("token is empty");
            Key key = new SecretKeySpec(REFRESH_TOKEN_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody().getIssuer();
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid Refresh Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired Refresh Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported Refresh Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        } catch (Exception e) {
            log.info("Exception", e);
        }
        return null;
    }
    private Authentication setAuthentication(String[] roles) {
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);
        return new UsernamePasswordAuthenticationToken("", "", authorities);
    }

    private String[] convertToRoleList(String roleList){
        String trim = roleList.substring(1, roleList.length()-1);
        String[] roles = trim.split(",");
        List<String> roleCd = new ArrayList<>();
        List<String> roleNm = new ArrayList<>();

        for (String role : roles) {
            roleCd.add(role.split("=")[0].trim());
//            roleCd.add("ROLE_"+role.split("=")[0].trim());
            roleNm.add(role.split("=")[1]);
        }

        return roleCd.toArray(String[]::new);
    }
}
