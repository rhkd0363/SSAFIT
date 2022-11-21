<template>
  <div style="margin: 2%;">
    <h1>My Following</h1>
    <b-input-group style="width: 70%; margin: 1% 15%;">
      <b-input  type="text" v-model="search_name" />
      <b-button variant="info" style="width: 10%;" @click="searchFollowingList">검 색</b-button>
    </b-input-group>
    <div class="card" style="border-left: 0; border-right: 0;">
      <div class="table-responsive">
        <table class="table align-items-center mb-0">
          <thead>
            <tr style="text-align: center;">
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2" colspan="3">User Info</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" colspan="2" >팔로잉</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" colspan="2">팔로워</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" colspan="3" >Profile</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" colspan="2" >Follow</th>
            </tr>
          </thead>
          <tbody v-for="user in users" :key="user.user_id">
            <follow-item :user="user"></follow-item>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
  
  <script>
import FollowItem from "./FollowItem.vue";
import { mapState } from "vuex";

export default {
  name: "FollowingList",
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
    searchFollowingList() {
      let searchCondition = {
        search_name: this.search_name.trim(),
        user_id: this.user.user_id
      };
      this.$store.dispatch("searchFollowingList", searchCondition);
    }
  },
  created() {
    this.searchFollowingList();
  }
};
</script>
  
  <style>
</style>