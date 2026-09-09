<script lang="ts" setup>
import LogoVertical from '@/components/logo/LogoVertical.vue';

import { ref } from 'vue';
import router from '@/router/index.ts';
import { login } from '@/services/auth-service';
import Button from '@/components/button/Button.vue';
import TextInput from '@/components/text-input/TextInput.vue';

const email = ref('');
const password = ref('');

async function handleLogin() {
  try {
    await login(email.value, password.value);
    router.push({ name: 'home' });
  } catch (error) {
    console.log(`Error logging in: ${error}`);
  }
}

async function goToRegisterView() {
  router.push({ name: 'register' });
}
</script>

<template>
  <section>
    <header>
      <LogoVertical></LogoVertical>
      <h2 id="subtitle">Glad to see you here!</h2>
    </header>

    <form id="login" action="" method="get" @submit.prevent="handleLogin">
      <TextInput
        v-model="email"
        type="email"
        name="email"
        autocomplete="email"
        placeholder="Email"
      ></TextInput>

      <TextInput
        v-model="password"
        type="password"
        name="password"
        autocomplete="current-password"
        placeholder="Password"
      ></TextInput>

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
