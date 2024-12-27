import { createApp } from 'vue'
import App from './App.vue'
import router from './js/common-interceptor'
import {useRouter} from "vue-router";
const app = createApp(App)

app.config.globalProperties.$router = useRouter();

app.use(router)
app.mount('#app')

