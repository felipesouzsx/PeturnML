<script lang="ts" setup>
import { apiUrl } from '@/config/api';
import { createPost } from '@/services/post-service';
import type { PostStatus } from '@/model/post-status';
import { ref } from 'vue';

const props = defineProps<{
  status: PostStatus;
  onSubmitted: () => void;
  onError: (error: unknown) => void;
  onFulfilled: (response: void) => void;
  onReject: (reason: any) => void;
}>();

const petType = ref('CAT');
const petName = ref('');
const petImage = ref<File | null>(null);
const title = ref('');
const description = ref('');

async function handleSubmitPost() {
  if (!petImage.value) {
    return;
  }
  if (!petName.value) {
    petName.value = 'Unknown';
  }
  createPost(
    petName.value,
    petType.value,
    title.value,
    description.value,
    petImage.value,
    props.status,
  )
    .then(props.onFulfilled, props.onReject)
    .catch(props.onError);
  props.onSubmitted();
}

// Takes the image from the HTML element and puts it into the ref obj
function handleImage(event: Event) {
  const input = event.target as HTMLInputElement;
  petImage.value = input.files?.[0] ?? null;
}
</script>

<template>
  <form :action="apiUrl('/posts')" method="post" v-on:submit.prevent="handleSubmitPost">
    <label for="petType">What kind of pet is it?</label>
    <select name="petType" v-model="petType" required="true">
      <option value="CAT">Cat</option>
      <option value="DOG">Dog</option>
      <option value="HAMSTER">Hamster</option>
      <option value="BIRD">Bird</option>
      <option value="RABBIT">Rabbit</option>
      <option value="GUINEA_PIG">Guinea Pig</option>
      <option value="TURTLE">Turtle</option>
    </select>

    <label for="petName">Do they have a name?</label>
    <input
      type="text"
      name="petName"
      :placeholder="
        props.status == PostStatus.FOUND ? `Leave blank if you don't know` : 'Type their name here'
      "
      v-model="petName"
      maxlength="50"
    />

    <label for="petImage">How do they look like?</label>
    <input
      type="file"
      name="petImage"
      @change="handleImage"
      accept="image/jpeg, image/jpg, image/png, image/webp"
      required
    />

    <label for="title">Let's make a post about it</label>
    <input
      type="text"
      placeholder="Title of the post"
      name="title"
      v-model="title"
      maxlength="100"
    />
    <textarea
      name="description"
      v-model="description"
      placeholder="Tell us what happened"
      maxlength="500"
      rows="10"
      cols="60"
    ></textarea>
    <input type="submit" value="Submit" />
  </form>
</template>
