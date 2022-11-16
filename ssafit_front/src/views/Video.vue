<template>
  <div>
    <div>
      <select v-model="body_part">
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>
      <input placeholder="검색어를 입력하세요" v-model="keyword"/>
      <button @click="showVideoList">검색</button>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Video",

  data() {
    return {
      body_part: "",
      keyword: "",
    }
  },

  methods: {
    showVideoList() {
      this.$store.dispatch("showVideoList", this.body_part, this.keyword);
    }
  }
}
</script>

<style>

</style>