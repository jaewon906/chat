<template>
  <button :class="buttonClass" :disabled="disabled" @click="click">
    <slot></slot>
  </button>
</template>

<script setup>
  import {computed} from "vue";

  const emit = defineEmits(['click'])
  const props = defineProps({
    sm:{
      type: Boolean,
      default: false
    },
    small:{
      type: Boolean,
      default: false
    },
    medium:{
      type: Boolean,
      default: false
    },
    large:{
      type: Boolean,
      default: false
    },
    xlarge:{
      type: Boolean,
      default: false
    },
    disabled:{
      type: Boolean,
      default: false
    }
  })
  function click(){
    if(!props.disabled)
      emit('click')
 }

  const buttonClass = computed(()=>{
    return {
      'ur-button': true,
      'ur-button-sm': props.small,
      'ur-button-medium': props.medium,
      'ur-button-large': props.large,
      'ur-button-xlarge': props.xlarge,
      'ur-button-disabled': props.disabled
    }
  })

</script>

<style scoped lang="scss">
.ur-button{
  margin: 0 0 0 20px;
  width: 120px;
  height: 40px;
  border-radius: 5px;
  border :none;
  transition: 0.1s background-color ease;
  background-color: rgb(21,112,239);
  color:white;
  font-weight: 400;
  font-size: 16px;
  cursor:pointer;
}
.ur-button-sm{
  width:80px;
}
.ur-button-medium{
  width:160px;
}
.ur-button-large{
  width:220px;
}
.ur-button-xlarge{
  width:300px;
}
.ur-button-disabled{
  background-color: #bdcdec;
  cursor:default;
}
.ur-button:hover{
  background-color: #bdcdec
}
</style>