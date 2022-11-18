<template>
  <div>
    <ul>
      <li v-for="video in videos" :key="video.video_id">
        <video-item :video="video"></video-item>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState } from "vuex";
import VideoItem from "@/components/video/VideoItem.vue";

export default {
  name: "FollowLikeVideo",
  components:{
    VideoItem,
  },
  computed: {
    ...mapState(["videos",'user'])
  },
  props: {
    follow_id: ""
  },
  created() {
      this.showFollowLikeVideoList();
  },
  methods: {
    showFollowLikeVideoList() {
        if(this.follow_id == '' | this.follow_id == null){
            this.follow_id = this.user.user_id // 마이페이지 좋아요 목록 임시 방편
        }
      this.$store.dispatch("showFollowLikeVideoList", this.follow_id);
    }
  }
};
</script>

<style>
</style>