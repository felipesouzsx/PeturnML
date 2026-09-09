<script lang="ts" setup>
import { PostStatus } from '@/types/post-status';

const props = withDefaults(
  defineProps<{
    status: PostStatus;
    highlightPostStatus?: boolean;
  }>(),
  {
    highlightPostStatus: false,
  },
);

type Status = 'missing' | 'found';

const icons = {
  highlighted: {
    missing: '/assets/search_off_24dp_000000_FILL1_wght400_GRAD0_opsz24.svg',
    found: '/assets/search_check_24dp_000000_FILL1_wght400_GRAD0_opsz24.svg',
  },
  normal: {
    missing: '/assets/search_off_24dp_8A8A8E_FILL1_wght400_GRAD0_opsz24.svg',
    found: '/assets/search_check_24dp_8A8A8E_FILL1_wght400_GRAD0_opsz24.svg',
  },
};

const iconSet = props.highlightPostStatus ? icons.highlighted : icons.normal;
const status: Status = props.status.toLowerCase() as Status;
const icon = iconSet[status];
</script>

<template>
  <span class="tag" :class="highlightPostStatus ? props.status.toLowerCase() : ''">
    <img :src="icon" alt="" />
    {{ status }}
  </span>
</template>

<style scoped>
@import url(./common.css);
.tag {
  text-transform: capitalize;
}

.found {
  background-color: var(--found-color);
  color: var(--color-primary);
}

.missing {
  background-color: var(--missing-color);
  color: var(--color-primary);
}
</style>
