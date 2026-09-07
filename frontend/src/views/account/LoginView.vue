<script lang="ts" setup>
import LogoVertical from '@/components/logo/LogoVertical.vue';

import { ref } from 'vue';
import router from '@/router/index.ts';
import { login } from '@/services/auth-service';
import Button from '@/components/button/Button.vue';

const email = ref('');
const password = ref('');

async function handleLogin() {
  try {
    await login(email.value, password.value);
    router.push('/home');
  } catch (error) {
    console.log(`Error logging in: ${error}`);
  }
}

async function goToRegisterView() {
  router.push('/register');
}
</script>

<template>
  <section>
    <header>
      <LogoVertical></LogoVertical>
      <h2 id="subtitle">Glad to see you here!</h2>
    </header>

    <form id="login" action="" method="get" @submit.prevent="handleLogin">
      <input
        class="text-input"
        v-model="email"
        type="email"
        name="email"
        id="email"
        placeholder="Email"
        autocomplete="email"
      />
      <input
        class="text-input"
        v-model="password"
        type="password"
        name="password"
        id="password"
        placeholder="Password"
        autocomplete="current-password"
      />

      <Button
        type="submit"
        variant="primary"
        icon="/assets/login_24dp_FFFFFF_FILL1_wght400_GRAD0_opsz24.svg"
      >
        Login
      </Button>
    </form>

    <div id="create-account">
      <span>New around here?</span>
      <Button
        variant="secondary"
        icon="/assets/person_add_24dp_3C3C434D_FILL1_wght400_GRAD0_opsz24.svg"
        :click="goToRegisterView"
      >
        Create Account
      </Button>
    </div>
  </section>
</template>

<style scoped>
@import url(./common.css);

#create-account {
  gap: var(--gap-big);
  user-select: none;
}
</style>
