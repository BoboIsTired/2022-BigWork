import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import './assets/css/icon.css'
import axios from 'axios';
axios.defaults.baseURL = '/api'
const app = createApp(App)
installElementPlus(app)
app
    .use(store)
    .use(router)
    .mount('#app')

