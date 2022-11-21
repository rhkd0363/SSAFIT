<template>
  <div class="ReviewList">
    <strong style="font-size: 30px; margin-bottom: 2%;">Review</strong>  
    <b-list-group style="width: 95%;">
      <review-item :review="review" v-for="review in reviews.slice((currentPage-1)*perPage,(currentPage-1)*perPage+perPage)" :key="review.review_id">
      </review-item>
    </b-list-group>

    <div style="width: 95%;">
        <b-form-textarea
        v-model="review_content"
        placeholder="Please Review..."
        rows="3"
        max-rows="5" style="margin: 1%; width: 98%;"
      ></b-form-textarea>
      <b-button variant="info" @click="createReview" style="width: 15%; height: 10%; align-self: flex-end;">리뷰 등록</b-button>
    </div>

    <div class="overflow-auto" style="margin: 2%;">
        <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="my-table">
        </b-pagination>
    </div>

  </div>
</template>

<script>
import { mapState } from "vuex";
import ReviewItem from "./ReviewItem.vue";

export default {
  name: "ReviewList",

  components: {
    ReviewItem
  },

  computed: {
    ...mapState(["reviews", "video", "user"]),
    rows(){
      return this.reviews.length
    }
  },

  data() {
    return {
      review_content: "",
      perPage: 10,
      currentPage : 1,
    };
  },
  ref: {
    reviewContent: ""
  },
  methods: {
    createReview() {
      if ((this.review_content == null) | (this.review_content.trim() == "")) {
        alert("리뷰 내용을 작성하세요.");
        return;
      }

      let review = {
        video_id: this.video.video_id,
        user_id: this.user.user_id,
        review_content: this.review_content,
        user_name: this.user.user_name
      };

      this.$store.dispatch("registReview", review);

      this.review_content = "";
    }
  }
};
</script>

<style>
.ReviewList{
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>