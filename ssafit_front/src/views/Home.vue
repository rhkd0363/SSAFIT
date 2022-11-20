<template>
  <div>
    <!-- <img name="son_pic" src="../assets/son.jpg" style="z-index: -1; opacity: 100%;width: 100%; padding: 0;"> -->
    <video src="../assets/production ID_4761426.mp4" autoplay loop muted style="z-index:-1; opacity: 90%; width: 100%; padding: 0; position: absolute;"></video>
      <div style="display: flex; flex-direction: row; align-items: center; height: 85vh; justify-content: center; flex-direction: column;">
        <h1 style="color: #87beff; background-color: #0000006b; border-radius: 100%;"><em>SSAFIT</em></h1>
        <br>
        <h3 style="color: #87beff;">Sound Mind In A Sound Body</h3>
        <br><br>
        <div style="display: flex; flex-direction: row;">
        <router-link to="/join"><b-button variant="light " v-if="!mode">회원 가입</b-button></router-link>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <router-link to="/login"><b-button variant="outline-light" v-if="!mode">로그인</b-button></router-link>
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