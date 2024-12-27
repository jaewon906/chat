<template>
  <button class="ur-button" @click="click">
    <slot></slot>
  </button>
</template>

<script setup>
  const emit = defineEmits(['click'])
 function click(){
   emit('click')
 }
</script>

<style scoped lang="scss">
.ur-button{
  margin: 0 0 0 20px;
  width: 100px;
  height: 50px;
  border-radius: 5px;
  border :none;
  transition: 0.1s background-color ease;
  background-color: rgb(21,112,239);
  color:white;
  font-weight: 400;
  font-size: 16px;
}
.ur-button:hover{
  background-color:#e1ebff
}
</style>