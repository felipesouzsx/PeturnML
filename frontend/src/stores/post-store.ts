import type PostModel from '@/model/post-model';
import { getOthersPosts, getYourPosts } from '@/services/post-service';
import { defineStore } from 'pinia';
import { ref } from 'vue';

export const usePostStore = defineStore('posts', () => {
  const myPosts = ref<PostModel[]>([]);
  const othersPosts = ref<PostModel[]>([]);

  const loadedMyPosts = ref(false);
  const loadedOthersPosts = ref(false);

  async function fetchMyPosts() {
    if (!loadedMyPosts.value) {
      let page = 0;
      myPosts.value = await getYourPosts(page);
      console.log(`myPosts.value = ${myPosts.value}`);

      loadedMyPosts.value = true;
    }
  }

  async function fetchOthersPosts() {
    if (!loadedOthersPosts.value) {
      let page = 0;
      othersPosts.value = await getOthersPosts(page);

      loadedOthersPosts.value = true;
    }
  }

  async function retrieveMyPosts(): Promise<PostModel[]> {
    await fetchMyPosts();
    return myPosts.value;
  }

  async function retrieveOthersPosts(): Promise<PostModel[]> {
    await fetchOthersPosts();
    return othersPosts.value;
  }

  async function getPost(id: string): Promise<PostModel | undefined> {
    let result =
      (await retrieveMyPosts()).find((post) => post.id === id) ??
      (await retrieveOthersPosts()).find((post) => post.id === id);

    console.log(result);
    return result;
  }

  function invalidateCache() {
    loadedMyPosts.value = false;
    loadedOthersPosts.value = false;
  }

  return {
    retrieveMyPosts,
    retrieveOthersPosts,
    getPost,
    invalidateCache,
  };
});
