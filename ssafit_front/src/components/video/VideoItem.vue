<template>
  <router-link :to="`/video/detail?video_id=${this.video.video_id}`">
    <div>
      <img :src="video.thumbnail_url" />
      <span>{{ video.video_title }}</span>
    </div>
  </router-link>
</template>

<script>
export default {
  name: "VideoItem",

  props: {
    video: ""
  },

  methods: {}
};
</script>

<style>
</style>