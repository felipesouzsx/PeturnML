<script lang="ts" setup>
import router from '@/router';
import Tag from '../tag/Tag.vue';

const props = defineProps<{
  title: string;
  status: string;
  postId: string;
  imageFilename: string;
}>();

const imgSrc: string = `${import.meta.env.VITE_API_URL}/images/${props.imageFilename}`;

function handleClick() {
  router.push(`/posts/${props.postId}`);
}
</script>

<template>
  <div class="post" v-on:click="handleClick">
    <img :src="imgSrc" alt="" srcset="" />
    <div id="post-info">
      <h1 id="post-title">{{ props.title }}</h1>
      <span id="date-info">01/01/2026 16:32</span>
    </div>
    <div id="info">
      <Tag icon="/assets/search_off_24dp_8A8A8E_FILL1_wght400_GRAD0_opsz24.svg">{{
        props.status
      }}</Tag>
      <Tag icon="/assets/map_24dp_8A8A8E_FILL1_wght400_GRAD0_opsz24.svg">Location</Tag>
    </div>
  </div>
</template>

<style scoped>
.post {
  background-color: var(--color-bg-tertiary);
  padding: var(--gap-small);
  transition: 100ms;
  border-radius: var(--border-radius-0);
  border: 1px solid var(--color-bg-secondary);

  display: flex;
  flex-direction: column;
  gap: var(--gap-small);

  box-shadow: 0px 4px 10px 1px rgba(0, 0, 0, 0.15);
}
.post:hover {
  cursor: pointer;
  opacity: 75%;
}

.post > img {
  aspect-ratio: 16/9;
  object-fit: cover;
  width: 100%;
  border-radius: var(--border-radius-1);
}

#post-info {
  display: flex;
  justify-content: space-between;
  place-items: center;
}
#post-info > #date-info {
  color: var(--color-secondary);
  text-align: right;
  width: fit-content;
}
#post-info > #post-title {
  font-size: var(--font-size-big);
  width: 100%;
}

#info {
  display: flex;
  gap: var(--gap-normal);
}
</style>
