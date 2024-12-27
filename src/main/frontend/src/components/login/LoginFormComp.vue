<template>
    <div class="login_form__wrapper">
      <div class="login_form__header">
        <span class="title">Sign in</span><br/>
        <span class="description">Please fill your ID, PW</span>
      </div>
      <div class="login_form__content">
        <ur-input placeholder="ID" type="text"></ur-input><br/>
        <ur-input placeholder="PW" type="password"></ur-input>
      </div>
      <div class="right-bottom-action">
        <slot name="right-bottom-action"/>
      </div>
      <div class="forgot_your__password">
        <div>
          <span>forgot your ID?</span>
          <span style="color: rgb(21,112,239)" @click="forwardfindPage('ID')">find your ID</span>
        </div>
        <div>
          <span>forgot your password?</span>
          <span style="color: rgb(21,112,239)" @click="forwardfindPage('PW')">find your password</span>
        </div>

      </div>
    </div>

</template>

<script setup>

import UrInput from "@/components/common/UrInput.vue";
import {useRouter} from "vue-router";

function forwardfindPage(pageType){
 console.log(this.$router.push())
 console.log(this.$route.push())
}

</script>

<style scoped lang="scss">
.title{
  font-weight: bold;
  font-size: 30px;
}
.description{
  color:gray;
}
.login_form__wrapper{
  margin-top: -100px;
  width:65%;
  height:400px;
}
.login_form__header{
  width:100%;
  height:20%;
  line-height: 35px;
}

.login_form__content{
  margin-top: 20px;
  width:100%;
  height:40%;
}
.right-bottom-action{
  width:100%;
  height:50px;
  >*{
    float: right;
  }
}
.forgot_your__password{
  margin-top: 40px;
  width:100%;
  height:50px;
  border-top: 1px solid gray;
  line-height: 20px;
  >div{
    margin-top: 20px;
    display: flex;
    justify-content: space-between;
  }
}
</style>