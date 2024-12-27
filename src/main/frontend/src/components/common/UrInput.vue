<template>
  <div class="ur-input__wrapper">
    <input class="ur-input"
           v-model="data.content"
           @keydown="onKeyDownEnter"
           :placeholder="placeholder"
           :type="type"
    />
    <slot name="button">
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
  defineProps(['placeholder', 'type'])
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
    width: 100%;
  }
  .ur-input__wrapper{
    width: 100%;
    height: 50px;
    display: flex;
    align-items: center;
  }
  input{
    height:100%;
    border-radius: 5px;
    border: 1px solid gray;
  }
  input:focus{
    background-color: #e1ebff;
    border:1px solid cornflowerblue;
    outline:1px solid cornflowerblue;
  }


</style>