<template>
  <router-link :to="`/video/detail?video_id=${this.video.video_id}`" style="text-decoration: none; color: black;">
    <div style="display: flex; flex-direction: column;">
      <img style="width: 220px; border-radius: 3%;" :src="video.thumbnail_url"/>
      <div style="align-self: baseline; width: 220px;">{{ video_title }}</div>
      <div style="align-self: baseline;"><small style="color: rgba(0,0,0,0.5);">{{channel_title}}</small></div>
      <div style="align-self: baseline;"><small style="color: rgba(0,0,0,0.5);">조회수 {{video.view_cnt}}회</small></div>
    </div>
  </router-link>
</template>

<script>
export default {
  name: "VideoItem",
  props: {
    video: ""
  },
  computed:{
    video_title() {
        if (this.video.video_title.length > 30) {
            return this.video.video_title.substr(0, 30) + '...';
        }
        return this.video.video_title;
    },
    channel_title() {
        if (this.video.channel_title.length > 18) {
            return this.video.channel_title.substr(0, 18) + '...';
        }
        return this.video.channel_title;
    }

  },
  methods: {}
};
</script>

<style>
</style>