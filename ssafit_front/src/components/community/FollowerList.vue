<template>
    <div>
      <input type="text" v-model="search_name" />
      <button @click="searchFollowerList">검색</button>
      <h1>팔로워 리스트</h1>
      <table border="1">
        <thead>
          <th>이름</th>
          <th>프로필 보기</th>
          <th>팔로우 버튼</th>
        </thead>
        <tbody v-for="user in users" :key="user.user_id">
          <follow-item :user="user"></follow-item>
        </tbody>
      </table>
    </div>
  </template>
    
    <script>
  import FollowItem from "./FollowItem.vue";
  import { mapState } from "vuex";
  
  export default {
    name: "FollowerList",
    components: {
      FollowItem
    },
    data() {
      return {
        search_name: ""
      };
    },
    computed: {
      ...mapState(["users", "user"])
    },
    methods: {
      searchFollowerList() {
        let searchCondition = {
          search_name: this.search_name.trim(),
          user_id: this.user.user_id
        };
        this.$store.dispatch("searchFollowerList", searchCondition);
      }
    },
    created() {
      this.searchFollowerList();
    }
  };
  </script>
    
    <style>
  </style>