<template>
  <button class="ur-button">
    <slot></slot>
  </button>
</template>

<script setup>

</script>

<style scoped lang="scss">
.ur-button{
  margin: 0 0 0 20px;
  width: 70px;
  height: 100%;
  border-radius: 5px;
  border :none;
  transition: 0.1s background-color ease;
}
.ur-button:hover{
  background-color:#e1ebff
}
</style>