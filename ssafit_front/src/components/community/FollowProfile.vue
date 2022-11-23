<template>
  <div style="display: flex; justify-content: center;">
    <b-card style="width: 100%; display: flex; background-color: #e1e1e1;">
      <div style="max-width: 100%; min-width: 50%;">
        <div>
          <div class="container" style="margin-bottom: 8%;">
            <div style="margin-top: 7%;">
              <img id="profileImg" :src="`${ followProfile.user_img }`" style="border-radius: 100%;  background-color: beige; width: 30%; margin-bottom: 2%;">
          </div>
          <h3><strong>{{ followProfile.user_name }}</strong></h3>
          <p>{{ followProfile.user_id }}</p>
          <hr>
          <div style="display: flex; justify-content: center">
            <div style="margin-right: 20%">
              <h2>{{ followProfile.followingCnt }}</h2>
              <p>FOLLOWING</p>
            </div>
            <div>
              <h2>{{ followProfile.followerCnt }}</h2>
              <p>FOLLOWER</p>
            </div>
          </div>
          <hr>
          <br>
          <div>
            <h2>{{ followProfile.user_email }}</h2>
            <p>EMAIL</p>
            <br>
            <h2>{{ followProfile.user_phone_number }}</h2>
            <p>PHONE-NUMBER</p>
          </div>
          <br><br>
          <b-button @click="showList">Show Liked Videos</b-button>
        </div>
      </div>
    </div>
  </b-card>
  <b-card style="min-width: 50%; max-width: 100%; padding: 0; background-color: #e1e1e1;">
      <b-card-header>
        <h2><strong>{{ followProfile.user_name }}님이 좋아요한 영상</strong></h2>
      </b-card-header>
      <follow-like-video :follow_id="followProfile.user_id" :visibleCheck="visibleCheck"></follow-like-video>
    </b-card>
</div>
</template>

<script>
import { mapState } from 'vuex'
import FollowLikeVideo from './FollowLikeVideo.vue';

export default {
    name : 'FollowProfile',

    components:{
        FollowLikeVideo
    },
    data(){
      return{
        visibleCheck : true,
      }
    },
    computed:{
        ...mapState([
          'followProfile'
        ])
    },
    methods:{
      showList(){
        console.log(this.visibleCheck);
        this.visibleCheck = !this.visibleCheck
      }
    }
}
</script>

<style>
</style>