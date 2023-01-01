import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../App.vue';
import InfoView from '@/components/organisms/InfoView.vue';

//Vue.use(VueRouter)

const routes = [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/info',
      name: 'info',
      component: InfoView
    }
]

const router = new createRouter({
    history : createWebHistory(process.env.BASE_URL),
    routes : routes
})

export default router
