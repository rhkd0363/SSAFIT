<template>
  <div style="display: flex; justify-content: center;">
    <div style="width: 80%;">
      <div>
        <div class="container">
          <div>
            <img id="profileImg" :src="`${ followProfile.user_img }`" style="border-radius: 100%;  background-color: beige; width: 30%">
          </div>
          <h3><strong>{{ followProfile.user_name }}</strong></h3>
          <p>{{ followProfile.user_id }}</p>
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
          <br>
          <div>
            <h2>{{ followProfile.user_email }}</h2>
            <p>EMAIL</p>
            <br>
            <h2>{{ followProfile.user_phone_number }}</h2>
            <p>PHONE-NUMBER</p>
          </div>
          <b-button @click="showList">Show Liked Videos</b-button>
        </div>
      </div>
    </div>
    <hr :class="{ 'visible': visibleCheck }" style="width: 3px; height: 80vh; background-color: black; margin-right: 3% ;">
    
    <div :class="{ 'visible': visibleCheck }">
      <div>
        <h2><strong>{{ followProfile.user_name }}님이 좋아요한 영상</strong></h2>
      </div>
      <follow-like-video :follow_id="followProfile.user_id"></follow-like-video>
    </div>
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
        this.visibleCheck = !this.visibleCheck
      }
    }
}
</script>

<style>
.visible{
  display: none;
}
</style>