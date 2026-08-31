import { computed, ref } from 'vue'
import { defineStore } from 'pinia'
import type { User } from '@/model/user'


export const useAuthStore = defineStore('auth', () => {
  const accessToken = ref<string | null>(null)
  const user = ref<User | null>(null)

  const isAuthenticated = computed(() => {
    return accessToken.value !== null
  })

  function setAuth(token: string, authenticatedUser: User) {
    accessToken.value = token
    user.value = authenticatedUser
  }

  function logout() {
    accessToken.value = null
    user.value = null
  }

  return {
    accessToken,
    user,
    isAuthenticated,
    setAuth,
    logout,
  }
})
