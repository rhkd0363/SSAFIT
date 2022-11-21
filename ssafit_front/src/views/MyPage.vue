<template>
  <div class="myPage">
    <div>
      <h1 style="text-align: center;">MY PAGE</h1>
      <hr />
      <div style="background-color: beige; border-radius: 1%;">
        <div style="text-align: center;">
          <strong style="font-size: 2vw;">ID : {{user.user_id}}</strong>
        </div>
        <hr style="margin-left: 2%; margin-right: 2%;" />
        <div style="display: flex; align-items: center;">
          <div style="width: 25%; margin: 0% 5% 0% 5%;">
            <img
              :src="`${ user.user_img }`"
              style="width: 100%; background-color: white; border-radius: 100%;"
            />
          </div>
          <div style="text-align: center; width: 50%;">
            <div style="display: flex; justify-content: space-around;">
              <div>
                <strong style="font-size: 4vw;">{{followerCnt}}</strong>
                <p>
                  <strong style="font-size: 2vw;">FOLLOWER</strong>
                </p>
              </div>
              <hr style="background-color: rgba(0,0,0,0.1); height: 9vw; width: 1.5px;" />
              <div>
                <strong style="font-size: 4vw;">{{followingCnt}}</strong>
                <p>
                  <strong style="font-size: 2vw;">FOLLOWING</strong>
                </p>
              </div>
            </div>
            <br />
            <div style="display: flex; justify-content: space-between; width: 100%;">
              <router-link to="/updateUser">
                <b-button
                  variant="primary"
                  style="padding-left: 50px; padding-right: 50px;"
                >EDIT USER INFO</b-button>
              </router-link>
              <router-link to="/updatePass">
                <b-button variant="danger" style="padding-left: 50px; padding-right: 50px;">EDIT PASSWORD</b-button>
              </router-link>
            </div>
          </div>
        </div>
        <hr style="margin-left: 2%; margin-right: 2%;" />
        <div style="margin: 4%; display: flex; justify-content: space-between;">
          <div>
            <h4>NAME : {{user.user_name}}</h4>
            <br />
            <h4>EMAIL : {{user.user_email}}</h4>
            <br />
            <h4>PHONE-NUMBER : {{user.user_phone_number}}</h4>
            <br />
          </div>
          <div style="justify-content: center; align-self: center;">
            <router-link to="/likeVideoList">
              <b-button variant="info">Watch Favorite Videos</b-button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from 'axios';

export default {
  name: "MyPage",

  data() {
    return {
      followerCnt : 0,
      followingCnt : 0,
    };
  },

  computed: {
    ...mapState(["user"])
  },

  methods: {},
  created(){
    axios({
        url: process.env.VUE_APP_REST_URL+"/followerCnt",
        method: "GET",
        params: {
          user_id : this.user.user_id
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        this.followerCnt = res.data
      });

    axios({
        url: process.env.VUE_APP_REST_URL+"/followingCnt",
        method: "GET",
        params: {
          user_id : this.user.user_id
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      }).then(res => {
        this.followingCnt = res.data
      });
  }
};
</script>

<style scoped>
.myPage {
  margin-left: 15%;
  margin-right: 15%;
}
</style>