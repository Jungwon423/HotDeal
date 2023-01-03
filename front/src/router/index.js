import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/components/pages/HomePage.vue';
import InfoView from '@/components/pages/InfoView.vue';

//Vue.use(VueRouter)

const routes = [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/productInfo/:productId',
      name: 'productInfo',
      component: InfoView
    }
]

const router = new createRouter({
    history : createWebHistory(), //process.env.BASE_URL
    routes : routes
})

export default router
