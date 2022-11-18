<template>
  <div>
    <b-navbar toggleable type="dark" variant="dark">
      <b-navbar-brand href="#">SSAFIT</b-navbar-brand>
      <div v-if="mode">
        <img id="profileImg" :src="`${ user.user_img }`">
        <!-- 환영인사 -->
        <span style="color: beige">{{user.user_name}}님 환영합니다</span>
      </div>
      <!-- 토글 버튼 -->
      <b-navbar-toggle target="navbar-toggle-collapse">
        <template #default="{ expanded }">
          <b-icon v-if="expanded" icon="chevron-bar-up"></b-icon>
          <b-icon v-else icon="chevron-bar-down"></b-icon>
        </template>
      </b-navbar-toggle>
      <b-collapse id="navbar-toggle-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item class="nav-item"><router-link to="/" class="nav-link">HOME</router-link></b-nav-item>
          <!-- 로그인 되어있을 때 -->
          <div v-if="mode">
            <b-nav-item class="nav-item"><router-link to="/video" class="nav-link">VIDEO</router-link></b-nav-item>
            <b-nav-item class="nav-item"><router-link to="/community" class="nav-link">COMMUNITY</router-link></b-nav-item>
            <b-nav-item class="nav-item"><router-link to="/myPage" class="nav-link">MYPAGE</router-link></b-nav-item>
            <b-nav-item class="nav-item"><a href="" @click="logout" class="nav-link">LOGOUT</a></b-nav-item>
          </div>
          <!-- 로그아웃 되어있을 때 -->
          <div v-else>
            <b-nav-item class="nav-item"><router-link to="/join" class="nav-link">SIGN UP</router-link></b-nav-item>
            <b-nav-item class="nav-item"><router-link to="/login" class="nav-link">LOG IN</router-link></b-nav-item>
          </div>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
    name: "HeaderNav",

    computed:{
      ...mapState([
        'mode',
        'user'
      ])
    },

    methods: {
      logout(){
        this.$store.dispatch('logout');
      }
    },
}
</script>

<style scoped>
#profileImg {
  width: 50px;
  height: 50px;
  margin-right: 10px;
  margin-bottom: 10px;
}

span {
  font-size: 25px;
}
</style>