import HomePage from '@/components/home/homePage.vue';
import LoginPage from '@/components/login/loginPage.vue';
import ChatPage from '@/components/chat/ChatPage.vue';

const routes = [
    {path: '/', name: 'home', component: HomePage},
    {path: '/login', name: 'login', component: LoginPage},
    {path: '/chat', name: 'chat', component: ChatPage},
];

export default routes;