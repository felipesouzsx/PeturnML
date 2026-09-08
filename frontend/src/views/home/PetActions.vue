<script setup lang="ts">
import Button from '@/components/button/Button.vue';
import router from '@/router';

function handleFoundPetClick() {
  router.push('/found-pet');
}

function handleLostPetClick() {
  router.push('/lost-pet');
}
</script>

<template>
  <div id="pet-actions">
    <h1>What happened?</h1>
    <div id="actions">
      <Button
        icon="\assets\search_check_24dp_000000_FILL1_wght400_GRAD0_opsz24.svg"
        icon-size="3rem"
        variant="secondary"
        :click="handleFoundPetClick"
      >
        I found a pet
      </Button>
      <Button
        icon="public\assets\search_off_24dp_000000_FILL1_wght400_GRAD0_opsz24.svg"
        icon-size="3rem"
        variant="secondary"
        :click="handleLostPetClick"
      >
        I lost a pet
      </Button>
    </div>
  </div>
</template>

<style scoped>
#pet-actions {
  aspect-ratio: 16/9;

  display: flex;
  flex-direction: column;
  gap: var(--gap-big);

  margin-top: var(--gap-big);

  place-items: center;
  justify-content: center;
}

#actions {
  display: flex;
  gap: var(--gap-normal);
  width: 100%;
}

#actions > button {
  aspect-ratio: 4/3;
  width: 100%;
  flex-direction: column;
  justify-content: center;

  color: var(--color-primary);
}
</style>
