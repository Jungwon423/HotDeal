import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/components/pages/HomePage.vue';
import ProductInfoPage from '@/components/pages/ProductInfoPage.vue';
import CategoryPage from '@/components/pages/CategoryPage.vue';

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
    }
]

const router = new createRouter({
    history : createWebHistory(), //process.env.BASE_URL
    routes : routes
})

export default router
