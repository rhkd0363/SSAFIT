<template>
  <tr>
    <td>{{user.user_name}}</td>
    <td :hidden="user.ref_follow == 1">
      <button @click="follow">Follow</button>
    </td>
    <td :hidden="user.ref_follow == 0">
      <button @click="unFollow">Un-Follow</button>
    </td>
  </tr>
</template>

<script>
import axios from "axios";

export default {
  name: "UserItem",
  data() {
    return {};
  },
  props: {
    user: ""
  },
  methods: {
    follow() {
      axios({
        url: process.env.VUE_APP_REST_URL + "/follow",
        method: "POST",
        params: {
          user_id: this.$store.state.user.user_id,
          follow_id: this.user.user_id
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        if (res.data == "success") {
          this.user.ref_follow = 1;
        } else {
          alert("Follow 실패");
        }
      });
    },
    unFollow() {
      axios({
        url: process.env.VUE_APP_REST_URL + "/follow",
        method: "DELETE",
        params: {
          user_id: this.$store.state.user.user_id,
          follow_id: this.user.user_id
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        if (res.data == "success") {
          this.user.ref_follow = 0;
        } else {
          alert("Un - Follow 실패");
        }
      });
    }
  }
};
</script>

<style>
</style>