import { createApp } from 'vue'
import './assets/index.css'
import App from './App.vue'
import { setupPlugins } from './plugins'
import router from './router'

const app = createApp(App);
setupPlugins(app)
app.use(router)
app.mount('#app')
