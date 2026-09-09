<script lang="ts" setup>
import Button from '@/components/button/Button.vue';
import LogoVertical from '@/components/logo/LogoVertical.vue';
import TextInput from '@/components/text-input/TextInput.vue';
import AuthenticationError from '@/errors/authentication-error';
import router from '@/router';
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
    returnToLoginView();
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

function returnToLoginView() {
  router.push('/');
}
</script>

<template>
  <section>
    <header>
      <LogoVertical></LogoVertical>
      <h2 id="subtitle">Let's get your account setup</h2>
    </header>

    <form action="" method="post" @submit.prevent="createAccount">
      <TextInput
        v-model="username"
        type="text"
        name="username"
        placeholder="Username"
        autocomplete="username"
      ></TextInput>
      <TextInput
        v-model="email"
        type="email"
        name="email"
        placeholder="Email"
        autocomplete="email"
      ></TextInput>
      <TextInput
        v-model="password"
        type="password"
        name="password"
        placeholder="Password"
        autocomplete="new-password"
      ></TextInput>
      <TextInput
        v-model="confirmPassword"
        type="password"
        name="confirm_password"
        placeholder="Confirm Password"
      ></TextInput>
      <Button type="submit" icon="/assets/person_add_24dp_FFFFFF_FILL1_wght400_GRAD0_opsz24.svg">
        Create Account
      </Button>
    </form>

    <div id="return-to-login">
      <span>Already have an account?</span>
      <Button
        variant="secondary"
        icon="/assets/login_24dp_C4C4C6_FILL1_wght400_GRAD0_opsz24.svg"
        :click="returnToLoginView"
      >
        Login
      </Button>
    </div>
  </section>
</template>

<style scoped>
@import url(./common.css);

#return-to-login {
  gap: var(--gap-big);
  user-select: none;
}
</style>
