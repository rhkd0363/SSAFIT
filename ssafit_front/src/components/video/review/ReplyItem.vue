
<template>
  <div :hidden="deleteCheck" style="margin-left: 5%;">
    <hr>
    <div style="display: flex; justify-content: space-between;">
      <div style="display: flex; width: 100%; ">
        <img :src="reply.user_img" style="width:35px; border-radius: 100%; background-color: #c9c1c1; align-self: center;">
        <div style="display: flex; flex-direction: column; align-items: baseline; margin-left: 1%;">
          <strong>{{reply.user_name}}</strong>
          <small style="font-size: 3px;">{{reply.reg_date}}</small>
        </div>
      </div>
      <div style="display: flex; margin-right: 1%;">
        <b-button @click="modifyReply" v-if="reply.user_id == user.user_id & updateCheck==false" variant="outline-primary" style="font-size: 5px; min-width: 50px; margin-right: 2%; height: 25pt;">수정</b-button>
        <b-button @click="deleteReply" v-if="reply.user_id == user.user_id & updateCheck==false" variant="outline-danger" style="font-size: 5px; min-width: 50px;margin-right: 2%; height: 25pt;">삭제</b-button>
        <b-button @click="updateReply" v-if="reply.user_id == user.user_id & updateCheck==true" variant="outline-primary" style="font-size: 5px; min-width: 50px;margin-right: 2%; height: 25pt;">완료</b-button>
        <b-button @click="cancleModify" v-if="reply.user_id == user.user_id & updateCheck==true" variant="outline-secondary" style="font-size: 5px; min-width: 50px;margin-right: 2%; height: 25pt;">취소</b-button>
      </div>
    </div>


    <div style="display: flex;">
      <input class="form-control" v-model="reply.reply_content" ref="reply_content" id="reply_content" style="background-color: white; border: 0;" :disabled="updateCheck == false">
    </div>

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
      setTimeout(() => {
        this.$refs.reply_content.focus()
      }, 0.1);
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