import router from '../routes'

router.beforeEach((to, from, next) => {
    console.log("ASDFAAAAAAA")
    //#TODO path이동 전 로직이 필요할 경우 작성
    next();
})


export default router