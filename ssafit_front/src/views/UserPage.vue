<template>
  <div>
    <div>
      <router-link to="/video" class="nav-link">영상 시청</router-link>
      <router-link to="/community" class="nav-link">커뮤니티</router-link>
    </div>
    <div class="text-center">
      <img name="son_pic" src="../assets/son.jpg" class="rounded">
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "UserPage",

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