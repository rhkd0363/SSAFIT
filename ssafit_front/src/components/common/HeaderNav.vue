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
          <li class="nav-item"><router-link to="/join" class="nav-link">SIGN UP</router-link></li>
          <li class="nav-item"><router-link to="/login" class="nav-link">LOG IN</router-link></li>
        </ul>
    </div>
  </nav>
</template>

<script>
export default {
    name: "HeaderNav",
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