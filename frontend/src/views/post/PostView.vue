<script lang="ts" setup>
import Tag from '@/components/tag/Tag.vue';
import type PostModel from '@/model/post-model';
import type { SimilarPostModel } from '@/model/similar-post-model';
import router from '@/router';
import { getSimilar } from '@/services/similarity-service';
import { usePostStore } from '@/stores/post-store';
import { ref, watch, computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const postData = ref<PostModel | null>(null);
const similarPosts = ref<SimilarPostModel[]>([]);
const { getPost } = usePostStore();
const loadingState = ref(false);
const loadingStateSimilarPosts = ref(false);

const petImg = computed(() =>
  postData.value ? `${import.meta.env.VITE_API_URL}/images/${postData.value.imageFilename}` : '',
);

async function loadPosts(postId: string) {
  try {
    postData.value = (await getPost(postId)) ?? null;
    loadingState.value = true;
    similarPosts.value = (await getSimilar(postId)) ?? [];
    loadingStateSimilarPosts.value = true;
  } catch (error) {
    console.log(error);
  }
}

function handleClickOnSimilarPost(postId: string) {
  router.push({
    name: 'posts',
    params: {
      id: postId,
    },
  });
}

// garantindo que a página atualize quando o id do post é atualizado.
// sem isso, o vue pensava que não tinha mudado a página e não atualizava nada.
// maluco.
watch(
  () => route.params.id,
  (id) => {
    if (typeof id !== 'string') {
      return;
    }
    loadPosts(id);

    similarPosts.value = [];
  },
  { immediate: true },
);
</script>

<template>
  <section>
    <section id="pet-info">
      <header>
        <h1>{{ postData?.pet.name }}</h1>
        <h2>Owned by {{ postData?.user.username }}</h2>
      </header>
      <img :src="petImg" alt="" />
      <ul id="tags">
        <Tag>{{ postData?.pet.type }}</Tag>
        <Tag>{{ postData?.status }}</Tag>
        <Tag class="long-tag">Location</Tag>
      </ul>
    </section>

    <section id="post">
      <h1>{{ postData?.title }}</h1>
      <p>{{ postData?.description }}</p>
    </section>

    <section id="similarity">
      <h1>Similar Pets</h1>
      <div
        class="similar-post"
        v-for="similarPost in similarPosts"
        v-on:click="handleClickOnSimilarPost(similarPost.postId)"
      >
        <h1>{{ similarPost.postTitle }}</h1>
        <p>{{ similarPost.postDescription }}</p>
        <span>Similarity {{ similarPost.similarity }}</span>
      </div>
    </section>
  </section>
</template>

<style scoped>
section {
  display: flex;
  flex-direction: column;

  gap: var(--gap-normal);
}

#pet-info {
  display: flex;
  flex-direction: column;
  gap: var(--gap-small);
}

#pet-info > header {
  width: 100%;

  display: flex;
  justify-content: space-between;

  align-items: center;
}

#pet-info > header > h2 {
  font-size: var(--font-size-normal);
  font-weight: 100;
  color: var(--color-secondary);
}

#pet-info > img {
  width: 100%;
  border-radius: var(--border-radius-0);
}

#post {
  display: flex;
  flex-direction: column;
  gap: var(--gap-normal);
}

#post > h1 {
  text-align: center;
}

#tags {
  list-style: none;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--gap-small);
}

.long-tag {
  grid-column: 1 / -1;
}

.similar-post {
  border: 1px solid whitesmoke;
}
</style>
