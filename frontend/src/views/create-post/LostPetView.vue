<script lang="ts" setup>
import router from '@/router/index.ts';
import PetForm from './PetForm.vue';
import { ref } from 'vue';
import LoadingScreen from './LoadingScreen.vue';
import { PostStatus } from '@/types/post-status.ts';

const creatingPost = ref<boolean>(false);

function handleSubmit(): void {
  creatingPost.value = true;
}
function handleError(error: unknown): void {
  creatingPost.value = false;
}
function handleFulfilled(result: void) {
  router.push({ name: 'home' });
}
</script>

<template>
  <section v-if="!creatingPost">
    <header>
      <img src="\assets\search_off_24dp_000000_FILL1_wght400_GRAD0_opsz24.svg" alt="" />
      <h1>I've lost my pet...</h1>
    </header>
    <PetForm
      v-bind:status="PostStatus.MISSING"
      v-on:submitted="handleSubmit"
      v-on:error="handleError"
      v-on:fulfilled="handleFulfilled"
      v-on:reject="handleError"
    ></PetForm>
  </section>
  <LoadingScreen v-else></LoadingScreen>
</template>

<style scoped>
@import url(./common.css);
</style>
