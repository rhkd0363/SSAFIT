<template>
  <div style="text-align: center;">
    <router-link to="/community">회원리스트</router-link> | 
    <router-link to="/community/following">팔로잉 리스트</router-link> | 
    <router-link to="/community/follower">팔로워 리스트</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Community",

  data() {
    return {
    }
  },

  methods: {
  }
}
</script>

<style>

</style>