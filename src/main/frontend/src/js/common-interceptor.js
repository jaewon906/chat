import routes from '../routes'
import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(),
    routes,
});


router.beforeEach((to, from ,next)=>{
    //#TODO path이동 전 로직이 필요할 경우 작성
    next();
})

export default router