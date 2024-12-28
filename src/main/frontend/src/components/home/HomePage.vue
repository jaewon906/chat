<template>
 <div class="home__wrapper">
   <ur-button small @click="forwardMainServicePage">Join chat</ur-button>
 </div>
</template>

<script setup>
import UrButton from "@/components/common/UrButton.vue";
import router from "@/routes";

function forwardMainServicePage(){
  router.push({
    path:'main'
  })
}
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