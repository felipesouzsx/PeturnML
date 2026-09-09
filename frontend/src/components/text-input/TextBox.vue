<script lang="ts" setup>
const props = defineProps<{
  name: string;
  placeholder?: string;
  maxLength: number;
  rows: number;
  cols: number;
}>();

const emit = defineEmits<{
  'update:modelValue': [value: string];
}>();
</script>

<template>
  <textarea
    class="text-input"
    :name="props.name"
    :placeholder="props.placeholder"
    :maxlength="props.maxLength"
    :rows="props.rows"
    :cols="props.cols"
    @input="emit('update:modelValue', ($event.target as HTMLInputElement).value)"
  ></textarea>
</template>

<style scoped>
@import url(./common.css);
</style>
