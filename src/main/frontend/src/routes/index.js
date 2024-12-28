import {createRouter, createWebHistory} from "vue-router";

const HomePage = () => import("@/components/home/HomePage.vue");
const LoginPage = () => import("@/components/login/LoginPage.vue");
const ChatPage = () => import("@/components/chat/ChatPage.vue");
const MainPage = () => import("@/components/main/MainPage.vue");

const routes = [
    {path: '', name: 'home', component: HomePage},
    {path: '/login', name: 'login', component: LoginPage},
    {path: '/main', name: 'main', component: MainPage},
    {path: '/chat', name: 'chat', component: ChatPage},
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;