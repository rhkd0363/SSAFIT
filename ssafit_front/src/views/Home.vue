<template>
  <div>
    <div style="height: 95.1vh;"> 
      <video src="../assets/HomeBanner.mp4" autoplay loop muted style="z-index:-1; opacity: 90%; width: 100%; padding: 0; margin: 0; position: absolute;"></video>
      <div style="display: flex; flex-direction: row; align-items: center; height: 85vh; justify-content: center; flex-direction: column;">
        <h1 style="font-size: 5vw; color: black; background-color: #0000006b; border-radius: 100%;"><em>SSAFIT</em></h1>
        <br>
        <h3 style="color: white; font-size: 2.5vw;">
          <em>
            <span style="color: #0068d3">System</span>.<span style="color: #3bddf7">out</span>.<span style="color: #9af720">println</span>(<span style="color: #08d3a4;">"Hello SSAFIT!"</span>);
          </em>
        </h3>
        <br><br>
        <div style="display: flex; flex-direction: row;">
          <router-link to="/join"><b-button variant="light " v-if="!mode"><strong>SIGN UP</strong></b-button></router-link>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <router-link to="/login"><b-button variant="outline-light" v-if="!mode"><strong>LOGIN</strong></b-button></router-link>
        </div>
      </div>
    </div>
    <footer-view></footer-view>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import FooterView from "../components/common/FooterView.vue";

export default {
  components:{
    FooterView
  },
  computed:{
    ...mapState(['mode'])
  },
}
</script>

<style>
</style>