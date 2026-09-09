<script lang="ts" setup>
import Button from '@/components/button/Button.vue';
import TextBox from '@/components/text-input/TextBox.vue';
import TextInput from '@/components/text-input/TextInput.vue';
import { apiUrl } from '@/config/api';
import { createPost } from '@/services/post-service';
import { PostStatus } from '@/types/post-status';
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

const imageUrl = ref<string | null>(null);

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

function handleImage(event: Event) {
  const input = event.target as HTMLInputElement;
  const file = input.files?.[0];
  if (!file) return;
  imageUrl.value = URL.createObjectURL(file);
  petImage.value = file ?? null;
}

function handleImageChange(event: Event) {
  const input = event.target as HTMLInputElement;
}
</script>

<template>
  <form :action="apiUrl('/posts')" method="post" v-on:submit.prevent="handleSubmitPost">
    <label for="petType">What kind of pet is it?</label>
    <select id="petType" name="petType" v-model="petType" required="true">
      <option value="CAT">Cat</option>
      <option value="DOG">Dog</option>
      <option value="HAMSTER">Hamster</option>
      <option value="BIRD">Bird</option>
      <option value="RABBIT">Rabbit</option>
      <option value="GUINEA_PIG">Guinea Pig</option>
      <option value="TURTLE">Turtle</option>
    </select>

    <label for="petName">Do they have a name?</label>
    <TextInput
      id="petName"
      type="text"
      name="petName"
      :placeholder="
        props.status == PostStatus.FOUND ? `Leave blank if you don't know` : 'Type their name here'
      "
      v-model="petName"
      max-length="48"
    ></TextInput>

    <label for="petImage">How do they look like?</label>
    <label
      tabindex="0"
      for="petImage"
      id="pet-image-display"
      :style="{
        backgroundImage: imageUrl ? `url(${imageUrl})` : undefined,
      }"
    >
      {{ imageUrl ? '' : 'Clique aqui para selecionar uma imagem' }}
    </label>
    <input
      id="petImage"
      type="file"
      name="petImage"
      @change="handleImage"
      accept="image/jpeg, image/jpg, image/png, image/webp"
      required
    />

    <label for="title">Let's make a post about it</label>
    <TextInput
      id="title"
      type="text"
      name="title"
      placeholder="Title of the post"
      v-model="title"
      max-length="48"
    ></TextInput>
    <TextBox
      v-model="description"
      name="description"
      placeholder="Tell us what happened"
      :max-length="500"
      :rows="10"
      :cols="10"
    ></TextBox>
    <Button type="submit" icon="/assets/send_24dp_FFFFFF_FILL1_wght400_GRAD0_opsz24.svg">
      Submit
    </Button>
  </form>
</template>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  gap: var(--gap-small);
}
form > label {
  margin-top: calc(var(--gap-normal) - var(--gap-small));
}

select {
  padding: var(--gap-small);
  border-radius: var(--border-radius-0);

  border: 1px solid var(--color-tertiary);
  box-shadow: 0px 4px 10px 1px rgba(0, 0, 0, 0.15);
}
select:hover,
select:focus {
  opacity: 65%;
  cursor: pointer;
  transform: translateY(-0.15rem);
  outline: none;
  transition: 100ms;

  * {
    opacity: 75%;
  }
}
select:active {
  transform: translateY(0);
  opacity: 100%;
}

input[type='file'] {
  display: none;
}
#pet-image-display {
  aspect-ratio: 4/3;
  display: flex;

  align-items: center;
  justify-content: center;

  background-color: var(--color-bg-secondary);
  border-radius: var(--border-radius-0);
  border: 1px solid var(--color-bg-secondary);

  cursor: pointer;

  box-shadow: 0px 4px 10px 1px rgba(0, 0, 0, 0.15);
  transition: 100ms;

  color: var(--color-secondary);

  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

#pet-image-display:hover {
  transform: translateY(-0.15rem);
  box-shadow: 0px 8px 10px 1px rgba(0, 0, 0, 0.1);
  opacity: 65%;
}

#pet-image-display:active {
  transform: translateY(0.15rem);
  opacity: 100%;
}
</style>
