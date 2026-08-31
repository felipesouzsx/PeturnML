<script lang="ts" setup>
import { ref } from 'vue'
import router from '@/router/index.ts'
import { useAuthStore } from '@/stores/auth-store'
import { login } from '@/services/auth-service'
import type { LoginResponse } from '@/model/login-response'
const authStore = useAuthStore()

const email = ref('')
const password = ref('')

async function handleLogin() {
  try {
    const response: LoginResponse = await login(email.value, password.value)
    authStore.setAuth(response.accessToken, response.user)
    router.push('/home')
  } catch (error) {
    console.log('Error logging-in')
  }
}
</script>

<template>
  <h1>PETURN</h1>
  <h2>Glad to see you here!</h2>

  <form action="" method="get" @submit.prevent="handleLogin">
    <input
      v-model="email"
      type="email"
      name="email"
      id="email"
      placeholder="Email"
      autocomplete="email"
    />
    <input
      v-model="password"
      type="password"
      name="password"
      id="password"
      placeholder="Password"
      autocomplete="current-password"
    />
    <input type="submit" value="Login" />
  </form>

  <p>New around here?</p>

  <RouterLink to="/create_account">Create Account</RouterLink>
</template>
