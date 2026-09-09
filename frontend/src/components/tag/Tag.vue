<script lang="ts" setup>
const props = withDefaults(
  defineProps<{
    icon?: string;
  }>(),
  {
    icon: '',
  },
);
</script>

<template>
  <span class="tag">
    <img :src="icon" alt="" />
    <slot></slot>
  </span>
</template>

<style scoped>
@import url(./common.css);
</style>
