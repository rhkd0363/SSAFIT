<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <!-- 사이드바 버튼 -->
      <b-button v-b-toggle.sidebar-1 style="background-color: unset; margin: 0; padding: ;">☰</b-button>
      
      <b-navbar-brand href="/">&nbsp;SSAFIT</b-navbar-brand>
      <!-- 토글 버튼 -->
      <b-navbar-toggle target="navbar-toggle-collapse">
        <template #default="{ expanded }">
          <b-icon v-if="expanded" icon="chevron-bar-up"></b-icon>
          <b-icon v-else icon="chevron-bar-down"></b-icon>
        </template>
      </b-navbar-toggle>
      <b-collapse id="navbar-toggle-collapse" is-nav>
        <!-- 로그인 되어있을 때 -->
        <b-navbar-nav v-if="mode">
          <b-nav-item class="nav-item">
            <router-link to="/video" class="nav-link"><em>VIDEO</em></router-link>
          </b-nav-item>
          <b-nav-item class="nav-item">
            <router-link to="/community" class="nav-link"><em>COMMUNITY</em></router-link>
          </b-nav-item>
          <b-nav-item class="nav-item">
            <router-link to="/trekking" class="nav-link"><em>TREKKING</em></router-link>
          </b-nav-item>
          <b-nav-item class="nav-item">
            <router-link to="/surfing" class="nav-link"><em>SURFING</em></router-link>
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-if="mode">
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <img :src=user.user_img style="width: 30px; background-color: white; border-radius: 100%;">&nbsp;
              <span style="color: white;">{{user.user_name}}</span>님 환영합니다 !
            </template>
            <b-dropdown-item href="/myPage">MYPAGE</b-dropdown-item>
            <b-dropdown-item href @click="logout">LOGOUT</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <!-- 로그아웃 되어있을 때 -->
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item class="nav-item">
            <router-link to="/login" class="nav-link">LOGIN</router-link>
          </b-nav-item>
          <b-nav-item class="nav-item">
            <router-link to="/join" class="nav-link">SIGN UP</router-link>
          </b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <!-- 사이드바 -->
    <b-sidebar id="sidebar-1" title="SSAFIT" shadow>
      <hr>
      <!-- 로그인 상태일때 -->
      <div class="px-3 py-2" v-if="mode">
        <ul style="margin: 0 0 40px 20px">
          <h6>VIDEO</h6>
          <li style="margin: 15px;"><router-link to="/video" style="text-decoration: none;">Video List</router-link></li>
          <li style="margin: 15px;"><router-link to="/myFavoriteVideo" style="text-decoration: none;">My Favorite Videos</router-link></li>
          <hr>   
        </ul>

        <ul  style="margin: 0 0 40px 20px">
          <h6>COMMUNITY</h6>
          <li style="margin: 15px;"><router-link to="/community" style="text-decoration: none;">User List</router-link></li>
          <li style="margin: 15px;"><router-link to="/community/following" style="text-decoration: none;">My Following</router-link></li>
          <li style="margin: 15px;"><router-link to="/community/follower" style="text-decoration: none;">My Follower</router-link></li>
          <li style="margin: 15px;"><router-link to="/community/board" style="text-decoration: none;">Community Board</router-link></li>
          <hr>   
        </ul>
        
        <ul  style="margin: 0 0 40px 20px" id="trekking">
            <h6>TREKKING</h6>
          <li style="margin: 15px;"><router-link to="/trekking" style="text-decoration: none;">Trekking Course</router-link></li>
          <hr>   
        </ul>
        
        <ul  style="margin: 0 0 40px 20px">
          <h6>SURFING</h6>
          <li style="margin: 15px;"><router-link to="/surfing" style="text-decoration: none;">Surfing Place</router-link></li>
          <hr>   
        </ul>

        <ul  style="margin: 0 0 40px 20px">
          <h6>PROFILE</h6>
          <li style="margin: 15px;"><router-link to="/myPage" style="text-decoration: none;">My Page</router-link></li>
          <li style="margin: 15px;"><router-link to="/updateUser" style="text-decoration: none;">Edit User Info</router-link></li>
          <li style="margin: 15px;"><router-link to="/updatePass" style="text-decoration: none;">Edit PassWord</router-link></li>
          <hr>   
        </ul>

        <ul  style="margin: 0 0 40px 20px">
          <a href @click="logout"><h6>LOGOUT</h6></a>
        </ul>

      </div>
      <!-- 로그아웃 상태일때 -->
      <div class="px-3 py-2" v-else>
        <ul  style="margin: 0 0 40px 20px">
          <router-link to="/join" style="text-decoration: none;"><h6>SIGN UP</h6></router-link>
        </ul>
        <ul  style="margin: 0 0 40px 20px">
          <router-link to="/login" style="text-decoration: none;"><h6>LOGIN</h6></router-link>
        </ul>

      </div>

    </b-sidebar>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "HeaderNav",

  computed: {
    ...mapState(["mode", "user"])
  },

  methods: {
    logout() {
      this.$store.dispatch("logout");
    }
  }
};
</script>

<style scoped>
.navbar{
  height: 50px;
}

</style>