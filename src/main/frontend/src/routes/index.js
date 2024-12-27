import HomePage from '@/components/home/homePage.vue';
import LoginPage from '@/components/login/loginPage.vue';
import ChatPage from '@/components/chat/ChatPage.vue';
import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {path: '/', name: 'home', component: HomePage},
    {path: '/login', name: 'login', component: LoginPage},
    {path: '/chat', name: 'chat', component: ChatPage},
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;