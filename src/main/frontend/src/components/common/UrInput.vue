<template>
  <div class="ur-input__wrapper">
    <input class="ur-input"
           v-bind:value="modelValue"
           @keydown="onKeyDownEnter"
           @input="onInput"
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

  const props = defineProps(['placeholder', 'type', 'modelValue'])
  const emit = defineEmits(['enter', 'update:modelValue'])
  const onKeyDownEnter = (e) => {
    if (props.modelValue !== '' && props.modelValue !== undefined && props.modelValue !== null) {
      if (e.keyCode === 13) {
        emit('enter')
      }
    }
  };

  const onInput = (e) => {
    emit('update:modelValue', e.target.value)
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