import { createApp } from 'vue'
import App from './App.vue'
import router from './js/common-interceptor'

const app = createApp(App)
app.use(router)
app.mount('#app')

