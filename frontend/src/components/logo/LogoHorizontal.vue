<template>
  <div class="logo">
    <img src="/assets/pets_24dp_FFFFFF_FILL1_wght400_GRAD0_opsz24.svg" alt="" />
    <h1>PETURN</h1>
  </div>
</template>

<style scoped>
@import url(./common.css);

.logo {
  display: flex;
  align-items: center;
  gap: var(--gap-normal);

  font-size: var(--font-size-normal);
}

.logo > img {
  width: 3rem;
}
</style>
