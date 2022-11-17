<template>
  <div :hidden="deleteCheck">
    <p>
      <input type="text" v-model="review.review_content" id="review_content" :disabled="updateCheck == false" /> {{review.user_id}} {{review.reg_date}}
      <span v-if="review.user_id == user.user_id">
        <button @click="modifyReview" v-if="updateCheck==false">수정하기</button>
        <button @click="deleteReview" v-if="updateCheck==false">삭제</button>
        <button @click="updateReview" v-if="updateCheck==true">수정완료</button>
        <button @click="cancleModify" v-if="updateCheck==true">취소</button>
      </span>
      <button @click="showReply">+</button>
    </p>
    <ul v-if="replys">
      <li v-for="reply in replys" :key="reply.reply_id">
        <reply-item :reply="reply"></reply-item>
      </li>
      <input type="text" placeholder="답글 입력" v-model="reply_content" />
      <button @click="createReply">답글 등록</button>
    </ul>
  </div>
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
      deleteCheck:false
    };
  },
  props: {
    review: ""
  },
  computed: {
    ...mapState(["user"])
  },
  methods: {
    showReply() {
      axios({
        url: process.env.VUE_APP_REST_URL+"/reply",
        method: "GET",
        params: { review_id: this.review.review_id },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
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
          this.showReply()
          this.reply_content=''
        }
      });
    },

    modifyReview() {
      this.updateCheck = true
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

<style>
</style>