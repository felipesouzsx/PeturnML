import { createRouter, createWebHistory } from 'vue-router';

import HomeScreen from '@/views/home/Home.vue';

import LostPet from '@/views/create-post/lost-pet/LostPet.vue';
import FoundPet from '@/views/create-post/found-pet/FoundPet.vue';

import Login from '@/views/Login.vue';
import Register from '@/views/Register.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/home', component: HomeScreen },
    { path: '/lost-pet', component: LostPet },
    { path: '/found-pet', component: FoundPet },
    { path: '/', component: Login },
    { path: '/register', component: Register },
  ],
});

export default router;
