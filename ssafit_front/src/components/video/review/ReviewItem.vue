<template>
  <b-list-group-item  :hidden="deleteCheck">
    <div style="display: flex; justify-content: space-between;">
      <div style="display: flex; width: 100%; ">
        <img :src="review.user_img" style="width:35px; border-radius: 100%; background-color: #c9c1c1; align-self: center;">
        <div style="display: flex; flex-direction: column; align-items: baseline; margin-left: 1%;">
          <strong>{{review.user_name}}</strong>
          <small style="font-size: 3px;">{{review.reg_date}}</small>
        </div>
      </div>
      <div style="display: flex; margin-right: 1%;">
        <b-button @click="showReply(replyCheck)" variant="outline-warning" style="font-size: 13px; min-width: 60px; margin-right: 2%; height: 25pt;" v-if="!replyCheck">Reply</b-button>
        <b-button @click="showReply(replyCheck)" variant="outline-warning" style="font-size: 13px; min-width: 60px; margin-right: 2%; height: 25pt;" v-else>Close</b-button>
        <b-button @click="modifyReview" v-if="review.user_id == user.user_id & updateCheck==false" variant="outline-primary" style="font-size: 5px; min-width: 50px; margin-right: 2%; height: 25pt;">수정</b-button>
        <b-button @click="deleteReview" v-if="review.user_id == user.user_id & updateCheck==false" variant="outline-danger" style="font-size: 5px; min-width: 50px;margin-right: 2%; height: 25pt;">삭제</b-button>
        <b-button @click="updateReview" v-if="review.user_id == user.user_id & updateCheck==true" variant="outline-primary" style="font-size: 5px; min-width: 50px;margin-right: 2%; height: 25pt;">완료</b-button>
        <b-button @click="cancleModify" v-if="review.user_id == user.user_id & updateCheck==true" variant="outline-secondary" style="font-size: 5px; min-width: 50px;margin-right: 2%; height: 25pt;">취소</b-button>
      </div>
    </div>

    <div style="display: flex;">
      <input class="form-control" v-model="review.review_content" ref="review_content" id="review_content" style="margin: 1%; background-color: white; border: 0;" :disabled="updateCheck == false">
    </div>

    <div v-if="replyCheck">
      <ul v-if="replys">
        <li v-for="reply in replys" :key="reply.reply_id">
          <reply-item :reply="reply"></reply-item>
        </li>
        <div style="margin-left: 5%;">
          <b-form-textarea v-model="reply_content" placeholder="Please Reply..." rows="3" max-rows="6"></b-form-textarea>
          <b-button variant="outline-info" @click="createReply" style="margin: 1%;">답글 등록</b-button>
        </div>
      </ul>
    </div>
  </b-list-group-item >
</template>

<script>
import ReplyItem from "./ReplyItem.vue";
import { mapState } from "vuex";
import axios from 'axios';

export default {
  name: "ReviewItem",
  components: {
    ReplyItem
  },
  data() {
    return {
      replys: null,
      reply_content: "",
      updateCheck : false,
      old_review_content : this.review.review_content,
      deleteCheck:false,
      replyCheck:false
    };
  },
  props: {
    review: ""
  },
  computed: {
    ...mapState(["user"])
  },
  methods: {
    showReply(reply_chk) {
      axios({
        url: process.env.VUE_APP_REST_URL+"/reply",
        method: "GET",
        params: { review_id: this.review.review_id },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        this.replyCheck = !reply_chk;
        this.replys = res.data;
      });
    },

    createReply() {
      if(this.reply_content == null | this.reply_content.trim() == ''){
                alert("답글 내용을 작성하세요.")
                return
            }

      let reply = {
        review_id: this.review.review_id,
        reply_content: this.reply_content,
        user_id: this.user.user_id,
        user_name : this.user.user_name
      };

      axios({
        url: process.env.VUE_APP_REST_URL+"/reply",
        method: "POST",
        params: reply,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        if (res.data == "success") {
          this.showReply(!this.replyCheck)
          this.reply_content=''
        }
      });
    },

    modifyReview() {
      this.updateCheck = true
      setTimeout(() => {
        this.$refs.review_content.focus()
      }, 0.1);
    },

    updateReview(){
      axios({
        url: process.env.VUE_APP_REST_URL+"/review",
        method: "PUT",
        params: this.review,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        if (res.data == "success") {
          this.updateCheck=false
          this.old_review_content = this.review.review_content
        }
      });
    },
    cancleModify(){
      this.updateCheck=false
      this.review.review_content = this.old_review_content
    },

    deleteReview(){
      axios({
          url: process.env.VUE_APP_REST_URL+"/review",
          method: "DELETE",
          params: {
            review_id : this.review.review_id
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

};
</script>

<style scoped>
#reply_content {
    width: 30%;
}
</style>