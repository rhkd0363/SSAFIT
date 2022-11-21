<template>
  <div style="text-align: center;" class="community">
    <router-link to="/community"><strong style="color: rgba(0,0,0,0.4); font-size: 20px;"> User List </strong></router-link>
    <strong style="color: black; font-size: 20px;"> | </strong>  
    <router-link to="/community/following"><strong style="color: rgba(0,0,0,0.4);; font-size: 20px;"> My Following</strong></router-link>
    <strong style="color: black; font-size: 20px;"> | </strong>  
    <router-link to="/community/follower"><strong style="color: rgba(0,0,0,0.4);; font-size: 20px;"> My Follower </strong></router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Community",

  data() {
    return {
    }
  },

  methods: {
  }
}
</script>

<style>
.community{
  margin: 1% 20%;
}
</style>