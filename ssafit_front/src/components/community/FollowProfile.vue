<template>
  <div style="display: flex; flex-direction: row; justify-content: space-between;">
    <div style="width: 50%;">
        <img id="profileImg" :src="`${ followProfile.user_img }`"><br> 
        이름 : {{followProfile.user_name}}<br> 
        이메일 : {{followProfile.user_email}}<br> 
        연락처 :  {{followProfile.user_phone_number}}<br> 
        팔로잉 수 : {{followProfile.followingCnt}} <br> 
        팔로워 수 : {{followProfile.followerCnt}} <br> 
    </div>
    <hr>
    <follow-like-video :follow_id="followProfile.user_id" style="width: 50%;"></follow-like-video>
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
    
    computed:{
        ...mapState([
          'followProfile'
        ])
    }
}
</script>

<style>

</style>