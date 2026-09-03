import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import type { UserModel } from '@/model/user-model';

export const useAuthStore = defineStore('auth', () => {
  const user = ref<UserModel | null>(null);

  const isAuthenticated = computed(() => {
    return user == null;
  });

  function setUser(authenticatedUser: UserModel) {
    user.value = authenticatedUser;
  }

  function logout() {
    user.value = null;
  }

  return {
    user,
    isAuthenticated,
    setUser,
    logout,
  };
});
