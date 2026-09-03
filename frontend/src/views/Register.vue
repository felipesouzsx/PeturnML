<script lang="ts" setup>
import AuthenticationError from '@/errors/authentication-error';
import { register } from '@/services/auth-service';
import { ref } from 'vue';

const username = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');

async function createAccount() {
  try {
    if (isPasswordInvalid()) {
      throw new AuthenticationError('Invalid password');
    }

    await register(username.value, email.value, password.value);
  } catch (error: unknown) {
    if (error instanceof AuthenticationError) {
      console.log(`Error authenticating: ${error.message}`);
    } else {
      console.log(error);
    }
  }
}

function isPasswordInvalid(): boolean {
  return password.value != confirmPassword.value || password.value == '';
}
</script>

<template>
  <h1>PETURN</h1>
  <h2>Hello! Let's get started</h2>

  <form action="" method="post" @submit.prevent="createAccount">
    <input
      v-model="username"
      type="text"
      name="username"
      id="username"
      placeholder="Username"
      autocomplete="username"
    />
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
      autocomplete="new-password"
    />
    <input
      v-model="confirmPassword"
      type="password"
      name="confirm_password"
      id="confirm_password"
      placeholder="Confirm Password"
      autocomplete="new-password"
    />
    <input type="submit" value="Create Account" />
  </form>
</template>
