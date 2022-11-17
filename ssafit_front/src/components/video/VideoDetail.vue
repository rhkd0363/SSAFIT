<template>
  <div v-if="video">
    <iframe width="560" height="315" :src="videoURL" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    영상 제목 : {{video.video_title}}<br>
    채널 명  :{{video.channel_title}}<br> 
    조회 수 : {{video.view_cnt}}<br>
    <button v-if="like" @click="removeLike">좋아요♥</button>
    <button v-else @click="registLike">좋아요♡</button>
    <review-list></review-list>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import ReviewList from './review/ReviewList.vue'

export default {
    name: 'VideoDetail',
    components:{
      ReviewList,
    },
    computed: {
        ...mapState([ 'video','like','user' ]),

        videoURL() {
            console.log(this.video)
            const videoId = this.video.video_id
            console.log(videoId)
            return `https://www.youtube.com/embed/${videoId}`
        }
    },
    methods: {
      registLike(){
        let data = {
          video_id : this.video.video_id,
          user_id : this.user.user_id
        }
        this.$store.dispatch('registLike',data)
      },
      removeLike(){
        let data = {
          video_id : this.video.video_id,
          user_id : this.user.user_id
        }
        this.$store.dispatch('removeLike',data)
      }
    },
    created(){
      let showData = {
        video_id : `${this.$route.query.video_id}`,
        user_id : this.user.user_id
      }
      this.$store.dispatch('showVideo',showData);
    }

}
</script>

<style>

</style>