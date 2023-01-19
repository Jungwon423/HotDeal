import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/mobile/pages/HomePage.vue';
import ProductInfoPage from '@/mobile/pages/ProductInfoPage.vue';
import CategoryPage from '@/mobile/pages/CategoryPage.vue';
import SearchPage from '@/mobile/pages/SearchPage.vue';
import testPage from '@/web/pages/HomePage.vue';

//Vue.use(VueRouter)

const routes = [
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

const router = new createRouter({
    history : createWebHistory(), //process.env.BASE_URL
    routes : routes
})

export default router
