<script setup lang="ts">
import PetActions from './PetActions.vue';
import OthersPosts from './OthersPosts.vue';
import YourPosts from './YourPosts.vue';
</script>

<template>
  <section>
    <PetActions></PetActions>

    <section>
      <h1>Your posts</h1>
      <YourPosts></YourPosts>
    </section>

    <section>
      <h1>Nearest reports</h1>
      <OthersPosts></OthersPosts>
    </section>
  </section>
</template>
