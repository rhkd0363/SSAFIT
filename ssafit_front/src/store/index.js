import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router'

Vue.use(Vuex)

const REST_URL = 'http://localhost:9999/api';

export default new Vuex.Store({
  state: {
    videos: [],
    video: '',
    reviews: [],
    replys: []
  },

  getters: {


  },

  mutations: {
    SET_VIDEOS(state, payload) {
      state.videos = payload;
    },
    SET_REVIEWS(state, payload){
      state.reviews = payload;
    },

  },

  actions: {
    idCheck({ commit }, payload) {
      console.log(payload);
      axios({
        url: REST_URL + '/user/idcheck',
        method: "GET",
        params: {
          user_id: payload
        }
      })
        .then((res) => {
          if (res.data === "success") {
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
        url: REST_URL + '/user',
        method: "POST",
        params: payload
      })
        .then((res) => {
          commit
        })
    },

    loginUser({ commit }, payload) {
      axios({
        url: REST_URL + '/user/login',
        method: "GET",
        params: payload
      })
        .then((res) => {
          console.log(res)
          if (res.data.message == "fail") {
            alert("아이디와 비밀번호를 확인해주세요");
          } else {
            sessionStorage.setItem("access-token", res.data['access-token']);
            sessionStorage.setItem("user_id", res.data.user.user_id);
            sessionStorage.setItem("user_name", res.data.user.user_name);
            router.push('/user')
          }
          // commit
        })
    },

    showVideoList({ commit }, payload) {
      axios({
        url: REST_URL + '/video',
        method: "GET",
        params: {
          keyword: payload.keyword,
          body_part: payload.body_part
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          commit('SET_VIDEOS', res.data)
        })
    },

    showReview({ commit }, payload) {
      axios({
        url: REST_URL + '/review',
        method: "GET",
        params: {
          video_id : payload
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          console.log(res)
          commit('SET_REVIEWS', res.data)
        })
    },
    registReview({commit},payload){
      console.log(payload)
      console.log('======================================')
      axios({
        url: REST_URL + '/review',
        method: "POST",
        params: payload,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          console.log(res)
        })
    }



  },

  modules: {
  }
})
