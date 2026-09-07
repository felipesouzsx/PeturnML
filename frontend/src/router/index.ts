import { createRouter, createWebHistory } from 'vue-router';

import LoginView from '@/views/account/LoginView.vue';
import RegisterView from '@/views/account/RegisterView.vue';
import HomeScreenView from '@/views/home/HomeView.vue';
import LostPetView from '@/views/create-post/LostPetView.vue';
import FoundPetView from '@/views/create-post/FoundPetView.vue';
import PostView from '@/views/post/PostView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', component: LoginView },
    { path: '/register', component: RegisterView },
    { path: '/home', component: HomeScreenView },
    { path: '/lost-pet', component: LostPetView },
    { path: '/found-pet', component: FoundPetView },
    { path: '/posts/:id', component: PostView },
  ],
});

export default router;
