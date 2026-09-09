<script setup lang="ts">
import Button from '../button/Button.vue';
import LogoHorizontal from '../logo/LogoHorizontal.vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();

function goBack() {
  router.back();
}
</script>

<template>
  <div id="container">
    <nav>
      <Button
        v-if="route.meta.showBackButton"
        variant="primary-inverted"
        icon="\assets\keyboard_backspace_24dp_000000_FILL1_wght400_GRAD0_opsz24.svg"
        :click="goBack"
      ></Button>
      <LogoHorizontal></LogoHorizontal>
    </nav>
  </div>
</template>

<style scoped>
#container {
  background-color: black;
  width: 100%;
  display: flex;
  justify-content: center;
}

nav {
  box-sizing: border-box;

  padding: var(--gap-normal);
  width: var(--website-width);

  position: relative;

  display: flex;
  justify-content: space-between;
}

nav > button {
  aspect-ratio: 1/1;

  padding: var(--gap-small);

  position: absolute;
  left: 0;
}

.logo {
  color: var(--color-bg-primary);
  width: 100%;
  justify-content: center;
}
</style>
