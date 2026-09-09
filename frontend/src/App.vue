<script setup lang="ts">
import { onMounted } from 'vue';

onMounted(async () => {
  await fetch('http://localhost:8080/auth/csrf', {
    method: 'GET',
    credentials: 'include',
  });
  console.log('Got CSRF token');
});
</script>

<template>
  <main>
    <RouterView />
  </main>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Cherry+Bomb+One&family=Commissioner:wght@100..900&display=swap');

main {
  --border-radius-0: 0.75rem;
  --border-radius-1: 0.375rem;

  --gap-smaller: 0.375rem;
  --gap-small: 0.75rem;
  --gap-normal: 1.5rem;
  --gap-big: 3rem;

  --found-color: #98e76e;
  --missing-color: #f88e84;

  --color-highlight: #ffffff;

  --color-bg-primary: #ffffff;
  --color-bg-secondary: rgba(121, 121, 129, 0.16);
  --color-bg-tertiary: rgba(121, 121, 129, 0.08);

  --color-primary: #000000;
  --color-secondary: #3c3c4399;
  --color-tertiary: #3c3c434d;

  --font-size-large: 48px;
  --font-size-big: 24px;
  --font-size-normal: 16px;
  --font-size-small: 7px;

  display: flex;
  flex-direction: column;
  place-items: center;

  font-family: 'Comissioner', sans-serif;
  color: var(--color-primary);
}

main > section {
  width: min(70ch, calc(100% - 2rem));
  margin-bottom: var(--gap-big);
  margin-top: var(--gap-big);
}
</style>
