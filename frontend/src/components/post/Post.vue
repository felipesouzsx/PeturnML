<script lang="ts" setup>
const props = defineProps<{
  title: string;
  status: string;
}>();
</script>

<template>
  <div class="post">
    <h1>{{ props.title }}</h1>
  </div>
  <div id="info">
    <span>{{ props.status }}</span>
    <span>Location</span>
  </div>
</template>
