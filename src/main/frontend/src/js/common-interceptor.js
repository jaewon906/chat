import router from '../routes'
import axios from "@/js/axios";

router.beforeEach((to, from, next) => {
    // 1. router에 지정된 페이지가 아닌곳에 접근 시 404 페이지로 리다이렉트
    if (!isAvailableMenu(to)) {
        router.push({
            path:'/error'
        })
    }

    // 2. loginPage, homePage를 제외한 path는 auth check를 진행.
    const permitAllURL = ['/', '/login', '/signup', '/find', '/error']; // #TODO 하드코딩 부분을 DB에서 permitAll된 URL 리스트로 변환 필요
    for (const URL of permitAllURL) {
        if (URL === to.path) {
            next();
            return;
        }
    }

    // 3.지정한 URL이 아닌 곳에 접근 시 권한 체크.
    axios.get('/auth/checkAuthentication')
        .then(res=>next())
        .catch(err=>{
            const status = err.status
            statusBranchProcessing(to, from, status)
        })

})

function statusBranchProcessing(to, from, status){ // status에 따른 분기처리

    console.log(status)
    if (status === 401) {
        alert("로그인이 필요한 서비스입니다.");
        router.push({
            path:'login'
        })
    }
    if (status === 403) {
        alert("접근 권한이 없습니다.");
        router.push({
            path: from.path
        })
    }
}

function isAvailableMenu(to){ // router에 지정된 메뉴 url로 접근여부 파악
    const routes = router.getRoutes();

    if(to.path === '/error') return true; // notfound path는 예외처리

    for (const route of routes) {
        if (to.path === route.path) {
            return true;
        }
    }
    return false;
}

export default router