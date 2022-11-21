<template>
  <div class="VideoList">
    <b-input-group style="width: 50%; margin: 1%;">
      <b-select v-model="body_part" @change="showVideoList">
        <option value="null">전체</option>
        <option value="전신">전신</option>
        <option value="가슴">가슴</option>
        <option value="등">등</option>
        <option value="어깨">어깨</option>
        <option value="하체">하체</option>
        <option value="복부">복부</option>
      </b-select>
      <b-input placeholder="검색어를 입력하세요" v-model="keyword" @keyup.enter="showVideoList" style="width: 500px;"/>
      <b-button @click="showVideoList">검색</b-button>
    </b-input-group>

    <b-list-group horizontal="md" style="display: flex; flex-wrap: wrap; max-width: 1580px">
      <b-list-group-item v-for="video in videos.slice((currentPage-1)*perPage,(currentPage-1)*perPage+perPage)" :key="video.video_id" style="border: 0px; border-radius: 3%;">
        <video-item :video="video"></video-item>
      </b-list-group-item>
    </b-list-group>

    <div class="overflow-auto" style="margin: 1%;">
      <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="my-table">

      </b-pagination>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import VideoItem from "./VideoItem.vue";

export default {
  name: "VideoList",

  components: {
    VideoItem,
  },

  data() {
    return {
      perPage: 18,
      currentPage : 1,
      body_part: null,
      keyword: "",
    };
  },

  created() {
      this.showVideoList()
  },

  computed: {
    ...mapState(["videos"]),
    rows(){
      return this.videos.length
    }
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

<style scoped>
.VideoList{
  margin-left: 5%;
  margin-right: 5%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>