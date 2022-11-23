<template>
  <div style="width: 100%;" :class="{ 'visible': visibleCheck }">
  <b-card-body style="min-height: 62vmin; display: flex; padding: 0;">
      <b-list-group horizontal="md" style="display: flex; flex-wrap: wrap; width: 110%; justify-content: center;">
          <b-list-group-item v-for="video in videos.slice((currentPage-1)*perPage,(currentPage-1)*perPage+perPage)" :key="video.video_id" style="border: 0px; border-radius: 3%;">
            <video-item :video="video"></video-item>
          </b-list-group-item>
        </b-list-group>
  </b-card-body>



    <b-card-footer>
      <div class="overflow-auto" style="width: 100%; display: flex; justify-content: center;">
        <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="my-table">
        </b-pagination>
      </div>
    </b-card-footer>
  </div>
</template>

<script>
import { mapState } from "vuex";
import VideoItem from "@/components/video/VideoItem.vue";

export default {
  name: "MyLikeVideo",

  components:{
    VideoItem,
  },
  computed: {
    ...mapState([
      "videos",
      'user'
    ]),

    rows(){
      return this.videos.length
    }
  },

  data(){
    return{
      perPage: 21,
      currentPage : 1,
    }
  },
  props: {
    follow_id: "",
    visibleCheck: '',
  },

  created() {
      this.showFollowLikeVideoList();
  },

  methods: {
    showFollowLikeVideoList() {
        if(this.follow_id == '' | this.follow_id == null) {
            this.follow_id = this.user.user_id // 마이페이지 좋아요 목록 임시 방편
        }
      this.$store.dispatch("showFollowLikeVideoList", this.follow_id);
    }
  }
};
</script>

<style scoped>
.visible{
  display: none;
}
</style>