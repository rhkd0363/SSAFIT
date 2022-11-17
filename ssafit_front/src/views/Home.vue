<template>
  <div class="text-bg-secondary p-3" >
    <img name="son_pic" src="../assets/son.jpg" class="rounded" style="z-index: -1; opacity: 70%; position: fixed; width: 100%;">
    <div>
      <div style="display: flex; justify-content: center; text-align: center; align-content: center; flex-direction: column;">
        <button v-if="!mode"><router-link to="/join">SIGN UP</router-link></button>
        <button v-if="!mode">LOGIN</button>
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
.text-center {
  text-align: center;
}

.main {
    text-align: center;
}
</style>