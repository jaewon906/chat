<template>
 <div class="home__wrapper">
   <ur-button small>Join chat</ur-button>
 </div>
</template>

<script setup>

import UrButton from "@/components/common/UrButton.vue";
</script>

<style scoped lang="scss">
.home__wrapper {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>