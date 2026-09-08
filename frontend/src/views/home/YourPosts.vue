<script lang="ts" setup>
import Post from '@/components/post/Post.vue';
import type PostModel from '@/model/post-model';
import { getYourPosts } from '@/services/post-service';
import { ref } from 'vue';

const posts = ref<Array<PostModel>>([]);

getYourPosts(0).then((result: Array<PostModel>) => {
  console.log(result);
  posts.value = result;
});
</script>

<template>
  <ul id="posts">
    <Post
      v-for="post in posts"
      :image-filename="post.imageFilename"
      :title="post.title"
      :status="post.status"
      :post-id="post.id"
    ></Post>
  </ul>
</template>
