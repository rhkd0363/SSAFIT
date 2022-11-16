<template>
  <div style="display: flex; flex-direction: row;">
    <div>
      <div>
        <select v-model="body_part">
          <option value="null">전체</option>
          <option value="전신">전신</option>
          <option value="가슴">가슴</option>
          <option value="등">등</option>
          <option value="어깨">어깨</option>
          <option value="하체">하체</option>
          <option value="복부">복부</option>
        </select>
        <input placeholder="검색어를 입력하세요" v-model="keyword" />
        <button @click="showVideoList">검색</button>
      </div>
      <ul>
        <li v-for="video in videos" :key="video.video_id">
          <video-list :video="video"></video-list>
        </li>
      </ul>
    </div>
    <video-detail></video-detail>
    <review-list></review-list>
  </div>
</template>

<script>
import { mapState } from "vuex";
import VideoList from "../components/video/VideoList.vue";
import VideoDetail from "../components/video/VideoDetail.vue";
import ReviewList from "../components/video/review/ReviewList.vue"

export default {
  name: "VideoSearch",

  components: {
    VideoList,
    VideoDetail,
    ReviewList
  },

  data() {
    return {
      body_part: null,
      keyword: ""
    };
  },

  created() {
    this.showVideoList()
  },

  computed: {
    ...mapState(["videos"])
  },

  methods: {
    showVideoList() {
      let searchCondition = {
        body_part: this.body_part,
        keyword: this.keyword
      };

      this.$store.dispatch("showVideoList", searchCondition);
    }
  },
};
</script>

<style>
</style>