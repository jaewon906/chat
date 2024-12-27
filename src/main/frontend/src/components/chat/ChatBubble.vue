<template>
  <div v-for="(item, idx) in data" :key='idx'>
    <div class="your_chatting__wrapper" v-if="item.sender == props.UUID">
      <div class="your_chatting">
        <div class="your_chatting__header">
          <div>{{ "ID: " + item.sender }}</div>
        </div>
        <div class="your_chatting__content">
          <div>{{ item.content }}</div>
        </div>
        <div class="your_chatting__footer">
          <div>{{ item.timestamp }}</div>
        </div>
      </div>
    </div>
    <div class="not_your_chatting__wrapper" v-else>
      <div class="not_your_chatting">
        <div class="not_your_chatting__header">
          <div>{{ "ID: " + item.sender }}</div>
        </div>
        <div class="not_your_chatting__content">
          <div>{{ item.content }}</div>
        </div>
        <div class="not_your_chatting__footer">
          <div>{{ item.timestamp }}</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
const props = defineProps(["UUID", "data"])
</script>

<style scoped lang="scss">
.your_chatting__wrapper {
  margin-top: 20px;
  width: 100%;
  min-height: 100px;
  display: flex;
  justify-content: flex-end;

}
.your_chatting{
  max-width: 60%;
}
.your_chatting__header{
  color: #727272;
  display: flex;
  justify-content: flex-end;
}
.your_chatting__content {
  padding: 10px;
  background-color: #e1ebff;
  word-wrap: break-word;
  > * {
    text-overflow: ellipsis;
  }
}
.your_chatting__footer {
  font-size: 10px;
  color: gray;
}

/* not your chatting */

.not_your_chatting__wrapper {
  margin-top: 20px;
  width: 100%;
  min-height: 100px;
  display: flex;
  justify-content: flex-start;
}
.not_your_chatting{
  max-width: 60%;
}
.not_your_chatting__header {
  color: #727272;
}
.not_your_chatting__content {
  padding: 10px;
  background-color: #efefef;
  word-wrap: break-word;
}
.not_your_chatting__footer {
  font-size: 10px;
  color: gray;
  float: right;
}
</style>