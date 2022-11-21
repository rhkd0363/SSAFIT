<template>
  <!-- <tr>
    <td>{{ user.user_name }}</td>
    <td>
      <button @click="showFollowProfile">프로필 보기</button>
    </td>
    <td :hidden="user.ref_follow == 1">
      <button @click="follow">Follow</button>
    </td>
    <td :hidden="user.ref_follow == 0">
      <button @click="unFollow">Un-Follow</button>
    </td>
  </tr> -->
  <tr>
    <td colspan="3">
      <div class="d-flex px-2 py-1">
          <img :src="user.user_img" class="avatar avatar-sm me-3" style="margin-right: 5%; width:60px; border-radius: 100%; background-color: #c9c1c1; align-self: center; justify-self: baseline;"/>
        <div class="d-flex flex-column justify-content-center" style="text-align: left;">
          <h5 class="mb-0"><strong>{{user.user_name}}</strong></h5>
          <p class="text-xs text-secondary mb-0">{{user.user_id}}</p>
        </div>
      </div>
    </td>
    <td colspan="2" style="vertical-align: middle;">
      <h4 class="mb-0" ><strong>{{user.followingCnt}}</strong></h4>
    </td>
    <td colspan="2" style="vertical-align: middle;">
      <h4 class="mb-0"><strong>{{user.followerCnt}}</strong></h4>
    </td>
    <td colspan="3"  style="vertical-align: middle;">
      <b-button variant="outline-warning" href @click="showFollowProfile" style="color: rgba(0,0,0,0.7);"><strong>프로필 보기</strong></b-button>
    </td>
    <td :hidden="user.ref_follow == 1" colspan="2" style="vertical-align: middle;">
      <b-button variant="outline-primary" @click="follow">Follow</b-button>
    </td>
    <td :hidden="user.ref_follow == 0" colspan="2" style="vertical-align: middle;">
      <b-button variant="primary" @click="unFollow">Follow</b-button>
    </td>
  </tr>
</template>

<script>
import axios from "axios";

export default {
  name: "FollowItem",

  data() {
    return {

    };
  },

  props: {
    user: "",
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
    },

    showFollowProfile() {  
      let followProfile = {
        ref_follow: this.user.ref_follow,
        user_email: this.user.user_email,
        user_id: this.user.user_id,
        user_img: this.user.user_img,
        user_name: this.user.user_name,
        user_phone_number: this.user.user_phone_number,
        followingCnt : 0,
        followerCnt : 0,
      };

      axios({
        url: process.env.VUE_APP_REST_URL + "/followingCnt",
        method: "GET",
        params: {
          user_id: this.user.user_id
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        console.log(res);
        followProfile.followingCnt = res.data;
        console.log(followProfile.followingCnt);
      });

      axios({
        url: process.env.VUE_APP_REST_URL + "/followerCnt",
        method: "GET",
        params: {
          user_id: this.user.user_id
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        followProfile.followerCnt = res.data;
      });

      this.$store.state.followProfile = followProfile;
      this.$router.push("followProfile");
    }
  }
};
</script>

<style>
</style>