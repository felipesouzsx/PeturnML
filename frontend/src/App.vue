<script setup lang="ts">
import { onMounted } from 'vue';

onMounted(async () => {
  const response = await fetch('http://localhost:8080/auth/csrf', {
    method: 'GET',
    credentials: 'include',
  });
  console.log('Got CSRF token');
});
</script>

<template>
  <main>
    <nav>
      <RouterLink to="/">Login</RouterLink>
      <RouterLink to="/register">Create Account</RouterLink>
    </nav>
    <RouterView />
  </main>
</template>

<style scoped></style>
