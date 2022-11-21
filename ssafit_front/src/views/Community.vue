<template>
  <div style="text-align: center;" class="community">
    <ul class="nav nav-pills nav-fill">
      <li class="nav-item">
        <router-link to="/community" class="nav-link"><strong style="color: rgba(0,0,0,0.4); font-size: 20px;">User List</strong></router-link>
      </li>
      <li class="nav-item">
        <router-link to="/community/following" class="nav-link"><strong style="color: rgba(0,0,0,0.4);; font-size: 20px;">My Following</strong></router-link>
      </li>
      <li class="nav-item">
        <router-link to="/community/follower" class="nav-link"><strong style="color: rgba(0,0,0,0.4);; font-size: 20px;">My Follower</strong></router-link>
      </li>
      <li class="nav-item">
        <router-link to="/community/board" class="nav-link"><strong style="color: rgba(0,0,0,0.4);; font-size: 20px;">Community Board</strong></router-link>
      </li>
    </ul>
    <br>
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