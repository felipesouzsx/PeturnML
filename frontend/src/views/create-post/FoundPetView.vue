<script setup lang="ts">
import router from '@/router/index.ts';
import { ref } from 'vue';
import PetForm from './PetForm.vue';
import { PostStatus } from '@/types/post-status.ts';
import LoadingScreen from './LoadingScreen.vue';

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
      <img src="\assets\search_check_24dp_000000_FILL1_wght400_GRAD0_opsz24.svg" alt="" />
      <h1>I found a pet</h1>
    </header>
    <PetForm
      v-bind:status="PostStatus.FOUND"
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
