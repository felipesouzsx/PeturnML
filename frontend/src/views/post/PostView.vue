<script lang="ts" setup>
import type PostModel from '@/model/post-model';
import type { SimilarPostModel } from '@/model/similar-post-model';
import router from '@/router';
import { getSimilar } from '@/services/similarity-service';
import { usePostStore } from '@/stores/post-store';
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const postData = ref<PostModel | null>(null);
const similarPosts = ref<SimilarPostModel[]>([]);
const { getPost } = usePostStore();
const loadingState = ref(false);
const loadingStateSimilarPosts = ref(false);

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
  router.push(`/posts/${postId}`);
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
  <section id="pet-info">
    <header>
      <h1>{{ postData?.pet.name }}</h1>
      <h2>Owned by {{ postData?.user.username }}</h2>
    </header>
    <img src="" alt="" />
    <ul id="tags">
      <li>{{ postData?.pet.type }}</li>
      <li>{{ postData?.status }} MM/DD/YYYY</li>
      <li>Location</li>
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
</template>

<style>
.similar-post {
  border: 1px solid whitesmoke;
}
</style>
