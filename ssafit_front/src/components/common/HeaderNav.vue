<template>
  <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a class="navbar-brand">SSAFIT</a>
    <!-- Toggle Button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
          <li class="nav-item"><router-link to="/" class="nav-link">HOME</router-link></li>
          <div v-if="mode" style="display: flex; flex-direction: row;">
            <li class="nav-item"><router-link to="/video" class="nav-link">VIDEO</router-link></li>
            <li class="nav-item"><router-link to="/community" class="nav-link">COMMUNITY</router-link></li>
            <li class="nav-item"><router-link to="/myPage" class="nav-link">MYPAGE</router-link></li>
            <li class="nav-item"><a href="" @click="logout" class="nav-link">LOGOUT</a></li>
          </div>
          <div v-else style="display: flex; flex-direction: row;">
            <li class="nav-item"><router-link to="/join" class="nav-link">SIGN UP</router-link></li>
            <li class="nav-item"><router-link to="/login" class="nav-link">LOG IN</router-link></li>
          </div>
        </ul>
    </div>
  </nav>
</template>

<script>
import { mapState } from 'vuex'
export default {
    name: "HeaderNav",
    computed:{
      ...mapState(['mode'])
    },
    methods: {
      logout(){
        this.$store.dispatch('logout');
      }
    },
}
</script>

<style>
a {
  text-decoration: none;
}

.container {
  margin: 0px 30px;
}

.view {
  width: 80%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: medium;
}

label {
  display: inline-block;
  width: 130px;
}
</style>