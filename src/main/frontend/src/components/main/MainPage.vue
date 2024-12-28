<template>
  <div class="chatroom__wrapper">
    <div class="chatroom"
         v-for="(list, idx) in data.chatRoom"
         @click="fn.forwardChatPage(list)"
    />
  </div>
</template>

<script setup>
  import {ref} from "vue";
  import router from "@/routes";

  const data = ref({
    chatRoom:[]
  })

  const fn ={
    forwardChatPage: (list)=>{
      router.push({
        path: list.url
      })
    }
  }

</script>

<style scoped lang="scss">
.chatroom__wrapper{

}
.chatroom{}
</style>