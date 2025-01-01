<template>
  <div class="login_form__wrapper">
    <div class="login_form__header">
      <span class="title">Sign In</span><br/>
      <span class="description">Please fill your ID, PW</span>
    </div>
    <div class="login_form__content">
      <ur-input @enter="login"
                :ref="$ref.userId"
                v-model="userData.userId"
                placeholder="ID"
                type="text"/><br/>
      <ur-input @enter="login"
                :ref="$ref.password"
                v-model="userData.password"
                placeholder="PW"
                type="password"/>
    </div>
    <sign-form-btn-wrapper>
      <template #left-bottom-action>
        <ur-button large @click="forwardSignUpPage">Sign up</ur-button>
      </template>
      <template #right-bottom-action>
        <ur-button main @click="login">Sign in</ur-button>
      </template>
    </sign-form-btn-wrapper>
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

import {ref} from "vue";
import SignFormBtnWrapper from "@/components/signup/SignFormBtnWrapper.vue";

const $ref = {
  userId: ref(),
  password: ref(),
};

const emit = defineEmits(['log-in', 'forward-signup-page', 'forward-find-page'])


const userData = ref({
  userId:'',
  password:'',
})
const login = () => {
  if(validation())
    emit('log-in', userData)
};

const forwardfindPage = (type) => { // 계정 정보 찾기 페이지 이동
  emit('forward-find-page')
};

const forwardSignUpPage = () => { // 회원가입 페이지 이동
  emit('forward-signup-page')
};

const validation = () => {
  const refs = ['userId', 'password']
  for (let ref of refs) {
    $ref[ref].value.hasError = false;
  }
  for (let ref of refs) { //#TODO : focus된 input hasError가 되지않는 문제
    if (userData.value[ref] === ""
        || userData.value[ref] === undefined
        || userData.value[ref] === null) {
      $ref[ref].value.hasError = true;
      $ref[ref].value.errorMessage = ref === 'userId' ? 'Please fill your ID' : 'Please fill your password';
      $ref[ref].value.focus();
      return false;
    }
  }
  return true;
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
.login_form_btn__wrapper{
  display: inline-block;
  width:100%;
  height:50px;
}
.right-bottom-action{
  display: inline-block;
  width:50%;
  height:auto;
  >*{
    float: right;
  }
}
.left-bottom-action{
  display: inline-block;
  width:50%;
  height:auto;
  >*{
    float: left;
  }
}
.middle-bottom-action{
  display: flex;
  justify-content: center;
  width: 100%;
  height:50px;
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