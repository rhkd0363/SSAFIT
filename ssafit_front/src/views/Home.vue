<template>
  <div>
    <!-- <img name="son_pic" src="../assets/son.jpg" style="z-index: -1; opacity: 100%;width: 100%; padding: 0;"> -->
    <video src="../assets/HomeBanner.mp4" autoplay loop muted style="z-index:-1; opacity: 90%; width: 100%; padding: 0; position: absolute;"></video>
      <div style="display: flex; flex-direction: row; align-items: center; height: 85vh; justify-content: center; flex-direction: column;">
        <h1 style="color: black; background-color: #0000006b; border-radius: 100%;"><em>SSAFIT</em></h1>
        <br>
        <h3 style="color: black;">To The World Of SSAFIT</h3>
        <br><br>
        <div style="display: flex; flex-direction: row;">
        <router-link to="/join"><b-button variant="light " v-if="!mode"><strong>SIGN UP</strong></b-button></router-link>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <router-link to="/login"><b-button variant="outline-light" v-if="!mode"><strong>LOGIN</strong></b-button></router-link>
        </div>
      </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  computed:{
    ...mapState(['mode'])
  },
}
</script>

<style>

</style>