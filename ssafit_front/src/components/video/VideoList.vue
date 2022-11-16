<template>
  <div>
        <img :src="video.thumbnail_url" @click="VideoDetail">
        <span @click="VideoDetail">{{video.video_title}}</span>
  </div>
</template>

<script>
export default {
  name: "VideoList",
  props: {
    video:''
  },
  methods: {
    VideoDetail(){
        this.$store.state.video = this.video
        console.log(this.$store.state.video);

        this.$store.dispatch("showReview",this.video.video_id);
    }
  },
};
</script>

<style>
</style>