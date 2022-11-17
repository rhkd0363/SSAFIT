
<template>
  <div :hidden="deleteCheck">
    &nbsp;&nbsp;&nbsp;&nbsp;→
    <span>내용 :  <input type="text" v-model="reply.reply_content" id="reply_content" :disabled="updateCheck == false" />
      작성일자 : {{reply.reg_date}} 작성자 : {{reply.user_name}}</span>
    <span v-if="reply.user_id == user.user_id">
      <button @click="modifyReply" v-if="updateCheck==false">수정하기</button>
      <button @click="deleteReply" v-if="updateCheck==false">삭제</button>
      <button @click="updateReply" v-if="updateCheck==true">수정완료</button>
      <button @click="cancleModify" v-if="updateCheck==true">취소</button>
    </span>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
export default {
    props:{
        reply :''
    },
    computed:{
      ...mapState(['user'])
    },
    data() {
      return {
        updateCheck:false,
        deleteCheck : false,
        old_reply_content: this.reply.reply_content,
      }
    },
    methods: {
    modifyReply() {
      this.updateCheck = true
    },
    updateReply(){
      axios({
        url: process.env.VUE_APP_REST_URL+"/reply",
        method: "PUT",
        params: this.reply,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        if (res.data == "success") {
          this.updateCheck=false
          this.old_reply_content = this.reply.reply_content
        }
      });
    },
    cancleModify(){
      this.updateCheck=false
      this.reply.reply_content = this.old_reply_content
    },

    deleteReply(){
      axios({
          url: process.env.VUE_APP_REST_URL+"/reply",
          method: "DELETE",
          params: {
            reply_id : this.reply.reply_id
          },
          headers: {
            "access-token": sessionStorage.getItem("access-token")
          }
        }).then(res => {
          if (res.data == "success") {
            this.deleteCheck = true
          }
        });
    }
  },
}
</script>

<style>

</style>