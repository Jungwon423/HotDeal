import { createApp } from 'vue'
import App from './App.vue'
// import router from './router'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import store from './store/index.js';

loadFonts()

const app = createApp(App)

var categoryMap = new Map();
categoryMap.set('all', '홈')
categoryMap.set('fashion_beauty', '패션 / 뷰티')
categoryMap.set('furniture_living', '가구 / 생활')
categoryMap.set('homeAppliances_computers', '가전 / 컴퓨터')
categoryMap.set('travel_car', '여행 / 자동차')

app.config.globalProperties.$categoryMap = categoryMap

app.use(vuetify)
  // .use(router)
  .use(store)
  .mount('#app')
