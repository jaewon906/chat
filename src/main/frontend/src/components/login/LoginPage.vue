<template>
  <div class="login__wrapper">
    <div class="left__side">

    </div>
    <div class="right__side">
      <LoginFormComp @log-in="loginFn.login"
                     @forward-signup-page="forwardFn.forwardSignUpPage"
                     @forward-find-page="forwardFn.forwardFindPage"
      />
    </div>
  </div>
</template>

<script setup>
import LoginFormComp from "@/components/login/LoginFormComp.vue";
import router from "@/routes";
import axios from "@/js/axios";

const url = {
  REST: '/login',
  login: '/login',
};

const loginFn = {
  login: (userData) => {
    axios.post(url.REST + url.login, userData.value)
        .then()
        .catch()
  }
};

const forwardFn = {
  forwardSignUpPage: ()=>{
    router.push({
      path:'/signup'
    })
  },
  forwardFindPage: ()=>{}
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