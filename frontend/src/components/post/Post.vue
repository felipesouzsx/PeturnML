<script lang="ts" setup>
import router from '@/router';
import Tag from '../tag/Tag.vue';
import PetStatusTag from '../tag/PetStatusTag.vue';
import type PostModel from '@/model/post-model.ts';

const props = withDefaults(
  defineProps<{
    post: PostModel;
    highlightStatus?: boolean;
  }>(),
  {
    highlightStatus: false,
  },
);

const imgSrc: string = `${import.meta.env.VITE_API_URL}/images/${props.post.imageFilename}`;

const creationDate: Date = new Date(props.post.creationDate);
const time: string = creationDate.toLocaleTimeString('pt-BR', {
  hour: '2-digit',
  minute: '2-digit',
});
const date: string = creationDate.toLocaleDateString('pt-BR', {
  day: '2-digit',
  month: '2-digit',
  year: '2-digit',
});

function handleClick() {
  router.push({
    name: 'posts',
    params: {
      id: props.post.id,
    },
  });
}
</script>

<template>
  <div class="post" v-on:click="handleClick">
    <img :src="imgSrc" alt="" srcset="" />
    <div id="post-info">
      <h1 id="post-title">{{ props.post.title }}</h1>
      <span id="date-info">{{ date }} {{ time }}</span>
    </div>
    <div id="info">
      <PetStatusTag :status="post.status" :highlight-post-status="highlightStatus"></PetStatusTag>
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
  white-space: nowrap;
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
