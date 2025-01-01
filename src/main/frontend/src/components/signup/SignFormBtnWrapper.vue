<template>
  <div class="login_form_btn__wrapper">
    <div class="left-bottom-action">
      <slot name="left-bottom-action"/>
    </div>
    <div class="right-bottom-action">
      <slot name="right-bottom-action"/>
    </div>
    <div class="middle-bottom-action">
      <slot name="middle-bottom-action"/>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped lang="scss">
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
</style>