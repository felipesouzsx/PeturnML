<script lang="ts" setup>
import type { ButtonVariants } from '@/types/button-variants';

const props = withDefaults(
  defineProps<{
    variant?: ButtonVariants;
    type?: 'button' | 'submit' | 'reset';
    icon?: string;
    click?: () => void;
  }>(),
  {
    variant: 'primary',
    type: 'button',
    icon: '',
    click: () => {},
  },
);
</script>

<template>
  <button :class="variant" :type="type" @click="click">
    <img :src="icon" alt="" />
    <slot></slot>
  </button>
</template>

<style scoped>
@import url(primary.css);
@import url(secondary.css);

button {
  all: unset;

  border-radius: var(--border-radius-0);
  width: fit-content;
  place-self: center;
  align-items: center;
  box-shadow: 0px 4px 10px 1px rgba(0, 0, 0, 0.15);

  padding: var(--gap-small) var(--gap-normal) var(--gap-small) var(--gap-normal);
  display: flex;
  gap: var(--gap-small);
  transition: 100ms;
}

button:hover,
button:focus {
  cursor: pointer;
  transform: translateY(-0.15rem);
  opacity: 65%;
}

button:active {
  transform: translateY(0.15rem);
  opacity: 100%;
}
</style>
