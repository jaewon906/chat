<template>
  <div class="container">
    <p style="font-size: 28px; font-weight: bold; margin:0 0 20px 0">채팅방</p>
    <div class="chat__content" ref="chatContent">
      <chat-bubble :data="messageQueue"
                   :UUID="UUID"
      />
    </div>
    <div class="chat_input__wrapper">
      <ur-input @enter="send"/>
    </div>
<!--    <div class="button__wrapper">-->
<!--      <ur-button @enter="send"/>-->
<!--    </div>-->
  </div>
</template>

<script setup>
/* eslint-disable */
  import UrInput from "@/components/common/UrInput.vue";
import ChatBubble from "@/components/chat/ChatBubble.vue";
import {onMounted, ref, nextTick} from "vue";
import SockJS from "sockjs-client"
import {Stomp} from '@stomp/stompjs'
import UrButton from "@/components/common/UrButton.vue";
  // init
  onMounted(()=>{
    openWebSocket();
  })

  async function openWebSocket(){
    data.socket = Stomp.over(new SockJS('http://localhost:8080/ws'))

    await data.socket.connect({}, ()=>{
      console.log("Connected")
      getMessage()
    })
  }
  /* not reactive object*/
  const data= {
    socket: null,
    url:{
      SUB: '/subscribe',
      PUB: '/publish',
      send: '/chat',
      getMessage: '/getMessage',
    },
  }
  /*end not reactive object*/

  /* reactive Object*/
  const messageQueue =ref([])
  const UUID = new Date() - (-0) // #TODO 임시
  /* end reactive Object*/

  /* $refs */
  const chatContent = ref(null)
  /* end $refs */

  function getMessage(){
    data.socket.subscribe("/subscribe/getMessage",(msg)=>{
      const parsedString = JSON.parse(msg.body)
      messageQueue.value.push(parsedString)
      nextTick(()=>{ // DOM 업데이트 후 스크롤 최하단으로 위치
        if(chatContent.value !== null)
          chatContent.value.scrollTop = chatContent.value.scrollHeight
      })
    })
  }
  function send(obj){
    obj.value.sender = UUID
    obj.value.timestamp = convertToUTC9()
    data.socket.send(data.url.PUB + data.url.send, {}, JSON.stringify(obj.value))
  }
  function convertToUTC9(){
    const date = new Date()
    date.setHours(date.getHours() + 9)
    return date;
  }

</script>


<style lang="scss" scoped>
.container{
  display: flex;
  flex-direction: column;
  align-items: center;
  width:100%;
  height:800px;
  padding:30px 10px 30px 10px;
  .chat__content{
    width:calc(80% - 22px);
    height:80%;
    display: flex;
    flex-direction: column;
    padding: 0 10px;
    border: 1px solid black;
    overflow:auto;
  }
  .chat__content::-webkit-scrollbar{
    width: 10px; /* 가로 스크롤바는 height로 설정 */
    background-color: #f4f4f4; /* 스크롤바 배경색 */

  }
  .chat__content::-webkit-scrollbar-track{
    background: #f4f4f4; /* 트랙 배경 */
  }
  .chat__content::-webkit-scrollbar-thumb{
    background-color: #888; /* 막대 색상 */
    border-radius: 6px; /* 모서리 둥글게 */
    border: 2px solid #f4f4f4; /* 트랙과 구분선 */
  }

  .chat_input__wrapper{
    width:80%
  }
  .button__wrapper{

  }
}
</style>
