<template>
    <div class="login_form__wrapper">
      <div class="login_form__header">
        <span class="title">Sign Up</span><br/>
        <span class="description">Please fill your information below</span>
      </div>
      <div class="login_form__content">
        <ur-input @enter="signUp"
                  :ref="$ref.userId"
                  v-model="userData.userId"
                  placeholder="ID"
                  type="text"/><br/>
        <ur-input @enter="signUp"
                  :ref="$ref.password"
                  v-model="userData.password"
                  placeholder="PW"
                  type="password"/><br/>
        <ur-input @enter="signUp"
                  :ref="$ref.email"
                  v-model="userData.email"
                  placeholder="E-mail"
                  type="email"/>
      </div>
      <sign-form-btn-wrapper>
        <template #middle-bottom-action>
          <ur-button main width-free @click="signUp">Join With Us</ur-button>
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
  email: ref(),
};

const userData = ref({
  userId:'',
  password:'',
  email:''
})
const emit = defineEmits(['sign-up'])

const signUp = () => {
  if(nullValidation() && typeValidation())
    emit('sign-up', userData)
};

const nullValidation = () => {
  const refs = ['userId', 'password', 'email']
  for (let ref of refs) {
    $ref[ref].value.hasError = false;
  }
  for (let ref of refs) {
    if (userData.value[ref] === ''
        || userData.value[ref] === undefined
        || userData.value[ref] === null) {
      $ref[ref].value.hasError = true;
      $ref[ref].value.errorMessage = 'This field is required';
      $ref[ref].value.focus();
      return false;
    }
  }
  return true;
};

const typeValidation = () => {
  const refs = ['userId', 'password', 'email']
  for (let ref of refs) {
    $ref[ref].value.hasError = false;
  }
  for (let ref of refs) {
    if (ref === 'userId' // ID 길이 검증
        && (userData.value[ref].length < 5
        || userData.value[ref].length > 30) ) {
      $ref[ref].value.hasError = true;
      $ref[ref].value.errorMessage = 'This field length is 5 to 30';
      $ref[ref].value.focus();
      return false;
    }
    if (ref === 'userId' // ID 적합성 검증
        && !userData.value[ref].match('^[a-zA-Z0-9]*$')) {
      $ref[ref].value.hasError = true;
      $ref[ref].value.errorMessage = 'This field must not contained spacial character';
      $ref[ref].value.focus();
      return false;
    }
    if (ref === 'password' // password 검증
        && (userData.value[ref].length < 8
        || userData.value[ref].length > 30) ) {
      $ref[ref].value.hasError = true;
      $ref[ref].value.errorMessage = 'This field length is 8 to 30';
      $ref[ref].value.focus();
      return false;
    }
    if(ref === 'email'){ //RFC 5322 표준에 가까운 이메일검증
      const regex = '^(?:[a-zA-Z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&\'*+/=?^_`{|}~-]+)*|"(?:\\\\[\x01-\x7f]|[^\\\\"])*")@[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?)*$'
      if (!userData.value[ref].match(regex)) {
        $ref[ref].value.hasError = true;
        $ref[ref].value.errorMessage = 'Email type is: ex) abc@example.com';
        $ref[ref].value.focus();
        return false;
      }
    }
  }
  return true;
};


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
  height:50%;
  min-height: 400px;
}
.login_form__header{
  width:100%;
  height:20%;
  line-height: 35px;
}

.login_form__content{
  margin-top: 20px;
  width:100%;
  height:45%;
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