<script lang="ts" setup>
import router from '@/router';

const props = defineProps<{
  title: string;
  status: string;
  postId: string;
}>();

function handleClick() {
  router.push(`/posts/${props.postId}`);
}
</script>

<template>
  <div class="post" v-on:click="handleClick">
    <h1>{{ props.title }}</h1>
    <div id="info">
      <span>{{ props.status }}</span>
      <span>Location</span>
    </div>
  </div>
</template>

<style scoped>
.post {
  border: 1px solid whitesmoke;
  padding: 1rem;
  transition: 100ms;
}
.post > * {
  margin: 0;
}
.post:hover {
  cursor: pointer;
  background-color: whitesmoke;
}
</style>
