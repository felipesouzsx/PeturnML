<script setup lang="ts">
import router from '@/router/index.ts';
import { ref } from 'vue';
import PetForm from '../PetForm.vue';
import { PostStatus } from '@/model/post-status.ts';

const creatingPost = ref<boolean>(false);

function handleSubmit(): void {
  creatingPost.value = true;
}
function handleError(error: unknown): void {
  creatingPost.value = false;
}
function handleFulfilled(result: void) {
  router.push('/home');
}
</script>

<template>
  <header>
    <h1>I found a pet</h1>
  </header>
  <PetForm
    v-if="!creatingPost"
    v-bind:status="PostStatus.FOUND"
    v-on:submitted="handleSubmit"
    v-on:error="handleError"
    v-on:fulfilled="handleFulfilled"
    v-on:reject="handleError"
  ></PetForm>
  <LoadingScreen v-else></LoadingScreen>
</template>
