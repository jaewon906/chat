<template>
  <div class="login__wrapper">
    <div class="left__side"/>
    <div class="right__side">
      <sign-up-form-comp ref="signUpComponent" @sign-up="signUpFn.signUp"/>
    </div>
  </div>
</template>

<script setup>
import SignUpFormComp from "@/components/signup/SignUpFormComp.vue";
import axios from "@/js/axios";

const url={
  REST: '/signup',
  signup: ''
}

const signUpFn = {
  signUp: (userData)=>{
    axios.post(url.REST + url.signup, userData.value)
        .then(res=>{})
        .catch()
  }
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