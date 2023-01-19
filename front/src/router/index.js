import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/mobile/pages/HomePage.vue';
import ProductInfoPage from '@/mobile/pages/ProductInfoPage.vue';
import CategoryPage from '@/mobile/pages/CategoryPage.vue';
import SearchPage from '@/mobile/pages/SearchPage.vue';
import testPage from '@/web/pages/HomePage.vue';
import MobileDetect from 'mobile-detect';
  

//Vue.use(VueRouter)

const m_routes = [ //모바일 라우터
    {
      path: '/',
      name: 'home',
      component: HomePage
    },
    {
      path: '/productInfo/:productId',
      name: 'productInfo',
      component: ProductInfoPage
    },
    {
      path: '/category',
      name: 'category',
      component: CategoryPage
    },
    {
      path: '/search',
      name: 'search',
      component: SearchPage
    },
    {
      path: '/test',
      name: 'test',
      component: testPage
    }
]


const w_routes = [ //웹 라우터
    {
      path: '/',
      name: 'home',
      component: testPage
    },
    {
      path: '/productInfo/:productId',
      name: 'productInfo',
      component: ProductInfoPage
    },
    {
      path: '/category',
      name: 'category',
      component: CategoryPage
    },
    {
      path: '/search',
      name: 'search',
      component: SearchPage
    },
]

const m_router = new createRouter({
    history : createWebHistory(), //process.env.BASE_URL
    routes : m_routes
})

const w_router = new createRouter({
  history : createWebHistory(), //process.env.BASE_URL
  routes : w_routes
})

let router;
const md = new MobileDetect(window.navigator.userAgent);
if(md.mobile()){
  console.log("CONNECTION WITH MOBILE");
  router = m_router;
}
else{
  console.log("CONNECTION WITH WEB");
  router = w_router;
}

export default router;
