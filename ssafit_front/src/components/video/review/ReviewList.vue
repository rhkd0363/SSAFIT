<template>
  <div>
    <ul>
        <li v-for="review in reviews" :key="review.review_id">
            <review-item :review="review"></review-item>
        </li>
    </ul>
    
    <div>
        <label for="review_content">리뷰 작성</label>
        <textarea class="form-control" placeholder="Please review." id="review_content" v-model="review_content"></textarea>
        <button @click="createReview">리뷰 등록</button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import ReviewItem from './ReviewItem.vue'

export default {
    name: 'ReviewList',

    components: {
        ReviewItem
    },

    computed: {
        ...mapState([
            'reviews',
            'video',
            'user'
        ])
    },

    data() {
        return {
            review_content : '',
        }
    },
    ref:{
        reviewContent:''
    },
    methods: {
        createReview(){
            if(this.review_content == null | this.review_content.trim() == ''){
                alert("리뷰 내용을 작성하세요.")
                return
            }

            let review = {
                video_id : this.video.video_id,
                user_id : this.user.user_id,
                review_content : this.review_content,
                user_name : this.user.user_name
            }

            this.$store.dispatch("registReview",review);

            this.review_content =''
        },
    },
}
</script>

<style>
#review_content {
    width: 30%;
}
</style>