import { createApp } from 'vue'
import App from './App.vue'
import router from './js/common-interceptor'
import {useRouter} from "vue-router";
import globalComponents from "@/js/global-components";
const app = createApp(App)

globalComponents(app)

app.config.globalProperties.$router = useRouter();

app.use(router)
app.mount('#app')

