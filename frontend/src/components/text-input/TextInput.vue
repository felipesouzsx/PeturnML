<script lang="ts" setup>
const props = defineProps<{
  modelValue?: string;
  placeholder?: string;
  type: string;
  name: string;
  autocomplete?: string;
}>();

const emit = defineEmits<{
  'update:modelValue': [value: string];
}>();
</script>

<template>
  <input
    class="text-input"
    :type="props.type"
    :name="props.name"
    :placeholder="props.placeholder"
    :autocomplete="props.autocomplete"
    @input="emit('update:modelValue', ($event.target as HTMLInputElement).value)"
  />
</template>

<style>
.text-input {
  all: unset;
  border: 1px solid var(--color-tertiary);
  text-align: left;
  padding: var(--gap-small);
  border-radius: var(--border-radius-0);
}

.text-input::placeholder {
  color: var(--color-tertiary);
}
</style>
