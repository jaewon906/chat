<template>
  <div class="ur-input__wrapper">
    <input class="ur-input" v-model="data.content" @keydown="onKeyDownEnter"/>
    <slot name="button">
      <ur-button @click="onClickBtn">전송</ur-button>
    </slot>
  </div>
</template>

<script setup>

  import {ref} from "vue";
  import UrButton from "@/components/common/UrButton.vue";

  const data=ref({
    sender: '',
    content: '',
    timestamp: ''
  })
  const emit = defineEmits(['enter'])
  const onKeyDownEnter = (e) => {
    if (data.value.content !== '' && data.value.content !== undefined && data.value.content !== null) {
      if (e.keyCode === 13) {
        emit('enter', data)
        data.value.content = ''
      }
    }
  };
  const onClickBtn = () => {
    if (data.value.content !== '' && data.value.content !== undefined && data.value.content !== null) {
      emit('enter', data)
      data.value.content = ''
    }
  };
</script>
<style scoped lang="scss">
  .ur-input{
    height:100%;
    width: calc(100% - 100px);
  }
  .ur-input__wrapper{
    margin-top: 20px;
    width: 100%;
    height: 40px;
    display: flex;
    align-items: center;
  }
  input{
  }
  input:focus{
    background-color: #e1ebff;
    border:1px solid cornflowerblue;
    outline:1px solid cornflowerblue;
  }


</style>