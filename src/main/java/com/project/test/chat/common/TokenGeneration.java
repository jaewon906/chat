package com.project.test.chat.common;

import com.project.test.chat.login.VO.LoginVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

@Component
public class TokenGeneration {

    @Value("${signature.access-token.key}")
    private String ACCESS_TOKEN_KEY;

    @Value("${signature.refresh-token.key}")
    private String REFRESH_TOKEN_KEY;

    public Cookie generateAccessToken(LoginVO loginVO) {

        //1. set Header
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        //2. set Claim(role) #TODO roleCd와 RoleNm을 각각의 list보다 Map으로 받는게 나을듯
        //2-1. String to List (role)
        List<String> roles = roleStringToList(loginVO.getRoleCd());
        List<String> roleNames = roleStringToList(loginVO.getRoleNm());
        Map<String, String> claim = new HashMap<>();
        for (int i = 0; i < roles.size(); i++) {
            claim.put(roles.get(i), roleNames.get(i));
        }

        //3. set IssuedAt, expiredDate
        Date now = new Date(System.currentTimeMillis());
        Date minutes_30 = new Date(System.currentTimeMillis() + 30 * 60 * 1000);

        //4. set key
        Key key = new SecretKeySpec(ACCESS_TOKEN_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        //5. build token
        String jwt = Jwts.builder()
                .setHeader(header)
                .setClaims(claim)
                .setIssuer(loginVO.getUserId())
                .setIssuedAt(now)
                .setExpiration(minutes_30)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Cookie cookie = new Cookie("ac", jwt);
        cookie.setPath("/");
        cookie.setDomain("localhost");
//        cookie.setAttribute("SameSite", "None");
//        cookie.setSecure(true); // true시 HTTPS에서만 적용
        cookie.setMaxAge(1800);
        return cookie;
    }

    public Cookie generateRefreshToken(LoginVO loginVO) {
        //1. set Header
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        Map<String, String> claim = new HashMap<>();

        //2. set IssuedAt, expiredDate
        Date now = new Date(System.currentTimeMillis());
        Date day_1 = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

        //3. set key
        Key key = new SecretKeySpec(REFRESH_TOKEN_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        //4. build token
        String jwt =  Jwts.builder()
                .setHeader(header)
                .setClaims(claim)
                .setIssuer(loginVO.getUserId())
                .setIssuedAt(now)
                .setExpiration(day_1)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Cookie cookie = new Cookie("rt", jwt);
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 24);
        cookie.setHttpOnly(true);
        cookie.setDomain("localhost");
//        cookie.setAttribute("SameSite", "None");
//        cookie.setSecure(true); // true시 HTTPS에서만 적용

        return cookie;
    }

    //#TODO resultMap으로 array_agg를 List<String>으로 받으려니 typeHandler가 없다고 에러나옴
    //#TODO 그래서 String으로 받아서 구분자로 쪼개는 불필요한 작업이 발생
    private List<String> roleStringToList(String role) {
        String substring = role.substring(1, role.length() - 1);
        String[] split = substring.split(",");
        return new ArrayList<>(List.of(split));
    }
}
