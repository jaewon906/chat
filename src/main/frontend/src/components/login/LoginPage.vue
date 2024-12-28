<template>
  <div class="login__wrapper">
    <div class="left__side">

    </div>
    <div class="right__side">
      <LoginFormComp>
        <template #right-bottom-action>
          <ur-button @click="sendData">sign in</ur-button>
        </template>
      </LoginFormComp>
    </div>
  </div>
</template>

<script setup>
import LoginFormComp from "@/components/login/LoginFormComp.vue";
import UrButton from "@/components/common/UrButton.vue";

function sendData(){

}
</script>

<style scoped lang="scss">
.login__wrapper{
  width:100%;
  height:100vh;
  display: flex;
}
.left__side{
  width:45%;
  height:100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-image: url("../../image/login_image.jpg")
}
.right__side{
  width:55%;
  height:100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>