<template>
  <div>
    <ul>
        <li v-for="review in reviews" :key="review.review_id">
            <review-item :review="review"></review-item>
        </li>
    </ul>
    
    <fieldset>
        <label for="review_content">리뷰 작성 : </label>
        <input type="text" id="review_content" v-model="review_content">
        <button @click="createReview">리뷰등록</button>
    </fieldset>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import ReviewItem from './ReviewItem.vue'

export default {
    name:'ReviewList',
    components:{
        ReviewItem
    },
    computed:{
        ...mapState(['reviews','video','user'])
    },
    data() {
        return {
            review_content : '',
        }
    },
    methods: {
        createReview(){
            let review = {
                video_id : this.video.video_id,
                user_id : sessionStorage.getItem("user_id"),
                review_content : this.review_content,
                user_name : sessionStorage.getItem("user_name")
            }

            this.$store.dispatch("registReview",review);
        }
    },
}
</script>

<style>

</style>