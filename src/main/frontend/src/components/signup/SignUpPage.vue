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
import router from "@/routes";

const url={
  REST: '/signup',
  signup: '/signup'
}

const signUpFn = {
  signUp: (userData)=>{
    axios.post(url.REST + url.signup, userData.value)
        .then(res=>{
          alert("Welcome to join us!!")
          router.push({
            path: '/login'
          })
        })
        .catch((e)=>{
          alert(JSON.parse(e.request.response).message)
        })
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
  min-width: 500px;
  height:100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>