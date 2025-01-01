import router from '../routes'
import axios from "@/js/axios";

router.beforeEach((to, from, next) => {
    // 1. loginPage, homePage를 제외한 path는 auth check를 진행.
    const permitAllURL = ['/', '/login', '/signup'] // #TODO 하드코딩 부분을 DB에서 permitAll된 URL 리스트로 변환 필요
    for (const URL of permitAllURL) {
        if (URL === to.path) {
            next();
            return;
        }
    }

    // 2.지정한 URL이 아닌 곳에 접근 시 권한 체크.
    axios.get('/checkAuthentication')
        .then(res=>next())
        .catch(err=>{
            const status = err.status
            statusBranchProcessing(to, from, status)
        })

    axios.get('/admin')
        .then()
        .catch()
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

export default router