import { createRouter, createWebHistory } from 'vue-router'

import HomeScreen from '@/views/home/HomeScreen.vue'
import LoginScreen from '@/views/login/LoginScreen.vue'
import CreateAccount from '@/views/create_account/CreateAccount.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/home', component: HomeScreen },
    { path: '/', component: LoginScreen },
    { path: '/create_account', component: CreateAccount },
  ],
})

export default router
