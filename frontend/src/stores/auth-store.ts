import { computed, ref } from 'vue'
import { defineStore } from 'pinia'
import type { User } from '@/model/user'

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User | null>(null)

  const isAuthenticated = computed(() => {
    return user == null
  })

  function setUser(authenticatedUser: User) {
    user.value = authenticatedUser
  }

  function logout() {
    user.value = null
  }

  return {
    user,
    isAuthenticated,
    setUser,
    logout,
  }
})
