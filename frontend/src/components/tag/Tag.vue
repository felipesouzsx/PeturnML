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
.tag {
  display: inline-flex;
  box-sizing: border-box;

  border: 1px solid var(--color-bg-secondary);
  border-radius: var(--border-radius-1);
  padding: var(--gap-smaller);
  width: 100%;

  place-content: center;
  align-items: center;

  gap: var(--gap-small);

  color: var(--color-secondary);
}
</style>
