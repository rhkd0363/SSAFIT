<template>
  <div v-if="video" class="VideoDetail">
    <b-button @click="historyBack">☰ 목록</b-button>
    <div style="margin: 1%; width: 98%; height: 37.5rem;">
      <iframe
        width="100%"
        height="100%"
        style="border-radius: 1%;"
        :src="videoURL"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen
      ></iframe>
    </div>
    <div style="margin-left: 3%; display: flex; justify-content: space-between;">
      <div>
        <strong style="font-size: 1.3vw;">{{ video.video_title }}</strong>
      </div>
      <div style="margin-right: 3%;">
        <strong>조회수 {{ video.view_cnt }}회</strong>
      </div>
    </div>
    
    <div style="margin-left: 3%; justify-content: space-between; display: flex;">
      <div style="margin: 1%;">
        <strong>{{ video.channel_title }}</strong>
      </div>
      <div style="margin-right: 3%;">
        <b-button variant="danger" v-if="like" @click="removeLike">좋아요♥</b-button>
        <b-button variant="danger" v-else @click="registLike">좋아요♡</b-button>
      </div>
    </div>
    <hr style="margin: 2%;">
    <review-list></review-list>
  </div>
</template>

<script>
import { mapState } from "vuex";
import ReviewList from "./review/ReviewList.vue";

export default {
  name: "VideoDetail",

  components: {
    ReviewList
  },

  computed: {
    ...mapState(["video", "like", "user"]),

    videoURL() {
      console.log(this.video);
      const videoId = this.video.video_id;
      console.log(videoId);
      return `https://www.youtube.com/embed/${videoId}`;
    }
  },

  created() {
    let showData = {
      video_id: `${this.$route.query.video_id}`,
      user_id: this.user.user_id
    };
    this.$store.dispatch("showVideo", showData);
  },

  methods: {
    registLike() {
      let data = {
        video_id: this.video.video_id,
        user_id: this.user.user_id
      };
      this.$store.dispatch("registLike", data);
    },

    removeLike() {
      let data = {
        video_id: this.video.video_id,
        user_id: this.user.user_id
      };
      this.$store.dispatch("removeLike", data);
    },
    historyBack(){
      window.history.back();
    }
  }
};
</script>

<style scoped>
.VideoDetail {
  margin-left: 22%;
  margin-right: 22%;
  display: flex;
  flex-direction: column;
  text-align: center;
  background-color: beige;
}
</style>