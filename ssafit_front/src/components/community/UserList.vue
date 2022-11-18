<template>
  <div>
    <input type="text" v-model="search_name"> <button @click="searchUserList">검색</button>
    <h1>회원 목록</h1>
    <table border="1">
        <thead>
            <th>이름</th>
            <th>팔로우 버튼</th>
        </thead>
        <tbody v-for="user in users" :key="user.user_id">
            <user-item :user="user"></user-item>
        </tbody>
    </table>
  </div>
</template>

<script>
import UserItem from './UserItem.vue'
import { mapState } from 'vuex'

export default {
    name: 'UserList',
    components:{
        UserItem,
    },
    data(){
        return{
            search_name : ''
        }
    },
    computed:{
        ...mapState(['users','user'])
    },
    methods:{
        searchUserList(){
            let searchCondition={
                search_name : this.search_name.trim(),
                user_id : this.user.user_id
            }
            this.$store.dispatch('searchUserList', searchCondition)
        }
    },
    created(){
        this.searchUserList()
    }
}
</script>

<style>

</style>