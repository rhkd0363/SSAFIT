<template>
    <div id="app">
        <header-nav></header-nav>
        <router-view></router-view>
    </div>
</template>

<script>
import HeaderNav from "./components/common/HeaderNav.vue";

export default {
    name: "App",
    components: {
        HeaderNav,
    },
}
</script>

<style>
/* 초기화 작업 */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    list-style: none;
}
#section{
    flex: 1;
}
</style>