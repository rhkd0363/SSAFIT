import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {


  },

  getters: {


  },

  mutations: {


  },

  actions: {
    idCheck({ commit },payload) {
      console.log(payload);
      axios({
        url: 'http://localhost:9999/api/user/idcheck',
        method: "GET",
        params: {
          user_id: payload
        }
      })
      .then((res) => {
        if(res.data === "success") {
          alert("사용 가능한 아이디입니다");
        }
        else {
          alert("중복된 아이디입니다");
        }
        commit
      })
    },

    joinUser({ commit }, payload) {
      axios({
        url: 'http://localhost:9999/api/user',
        method: "POST",
        params: payload
      })
      .then((res) => {
        commit
      })
    },

    loginUser({ commit }, payload) {
      console.log(payload)
      axios({
        url: 'http://localhost:9999/api/user/login',
        method: "GET",
        params: payload
      })
      .then((res) => {
        console.log(res)
        if(res.data.message == "fail"){
          console.log("로그인 실패");
          alert("아이디와 비밀번호를 확인해주세요");
        }else{
          console.log("로그인 성공");
          sessionStorage.setItem("access-token", res.data['access-token']);
          router.push('/user')
        }
        // commit
      })
    },

    showVideoList({ commit }, payload) {
      console.log(payload)

    }



  },

  modules: {
  }
})
