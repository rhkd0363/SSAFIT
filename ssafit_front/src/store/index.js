import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)


export default new Vuex.Store({
  state: {
    videos: null,
    video: '',
    reviews: [],
    replys: [],
    like: null,
    mode: false,
    user: null
  },

  getters: {
  },

  mutations: {
    SET_VIDEOS(state, payload) {
      state.videos = payload;
    },
    SET_VIDEO(state, payload) {
      state.video = payload;
    },

    SET_REVIEWS(state, payload) {
      state.reviews = payload;
    },
    SET_LIKE(state, payload) {
      state.like = payload
    },
    LOGOUT(state) {
      state.mode = false
    },
    LOGIN(state,payload) {
      state.user = payload
      state.mode = true
    }
  },

  actions: {

    joinUser({ commit }, payload) {
      axios({
        url: process.env.VUE_APP_REST_URL + '/user',
        method: "POST",
        params: payload
      })
        .then((res) => {
          commit
          alert('회원가입이 완료되었습니다.')
          router.push('/')
        })
    },

    loginUser({ commit }, payload) {
      axios({
        url: process.env.VUE_APP_REST_URL + '/user/login',
        method: "GET",
        params: payload
      })
        .then((res) => {
          console.log(res)
          if (res.data.message == "fail") {
            alert("아이디와 비밀번호를 확인해주세요");
          }
          else {
            sessionStorage.setItem("access-token", res.data['access-token']);
            commit('LOGIN',res.data.user)
            router.push('/')
          }
        })
    },

    logout({commit}) {
      commit('LOGOUT')
      sessionStorage.clear()
      router.push('/')
    },

    showVideo({ commit }, payload) {
      axios({
        url: process.env.VUE_APP_REST_URL + '/video/' + payload.video_id,
        method: "GET",
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          console.log(res)
          commit('SET_VIDEO', res.data)
        })

      axios({
        url: process.env.VUE_APP_REST_URL + '/review',
        method: "GET",
        params: {
          video_id: payload.video_id
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          console.log(res)
          commit('SET_REVIEWS', res.data)
        })

      axios({
        url: process.env.VUE_APP_REST_URL + '/likeOne',
        method: "GET",
        params: payload,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          console.log(res)
          if (res.data == 'success') {
            commit('SET_LIKE', true)
          }else{
            commit('SET_LIKE',false)
          }
        })
    },


    showVideoList({ commit }, payload) {
      axios({
        url: process.env.VUE_APP_REST_URL + '/video',
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
          console.log(res);
          commit('SET_VIDEOS', res.data)
        })
    },

    showReview({ commit }, payload) {
      axios({
        url: process.env.VUE_APP_REST_URL + '/review',
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

    registReview({ commit }, payload) {
      console.log(payload);

      axios({
        url: process.env.VUE_APP_REST_URL + '/review',
        method: "POST",
        params: payload,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          if(res.data == 'success'){
            this.dispatch('showReview',payload.video_id)
          }
        })
    },

    registLike({ commit }, payload) {
      axios({
        url: process.env.VUE_APP_REST_URL + '/like',
        method: "POST",
        params: {
          video_id: payload.video_id,
          user_id: payload.user_id
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          if (res.data == 'success') {
            commit('SET_LIKE', true)
          } else {
            alert('좋아요 실패')
          }
        })
    },
    removeLike({ commit }, payload) {
      axios({
        url: process.env.VUE_APP_REST_URL + '/like',
        method: "DELETE",
        params: {
          video_id: payload.video_id,
          user_id: payload.user_id
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          if (res.data == 'success') {
            commit('SET_LIKE', false)
          } else {
            alert('좋아요 취소 실패')
          }
        })
    },
  },

  modules: {
  },
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
    })
  ],
})
