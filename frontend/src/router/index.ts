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
    {
      name: 'login',
      path: '/login',
      component: LoginView,
    },
    {
      name: 'register',
      path: '/register',
      component: RegisterView,
      meta: { showBackButton: true },
    },
    { name: 'home', path: '/', component: HomeScreenView },
    {
      name: 'lost-pet',
      path: '/lost-pet',
      component: LostPetView,
      meta: { showBackButton: true },
    },
    {
      name: 'found-pet',
      path: '/found-pet',
      component: FoundPetView,
      meta: { showBackButton: true },
    },
    {
      name: 'posts',
      path: '/posts/:id',
      component: PostView,
      meta: { showBackButton: true },
    },
  ],
});

export default router;
