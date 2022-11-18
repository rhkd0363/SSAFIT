<template>
  <div>
    <div>
      <h1>마이 페이지</h1>
      <h3 class="table-dark">img</h3><img id="profileImg" :src="`${ user.user_img }`">
      <h3 class="table-dark">user_name</h3>{{user.user_name}}
      <h3 class="table-dark">user_id</h3>{{user.user_id}}
      <h3 class="table-dark">user_email</h3>{{user.user_email}}
      <h3 class="table-dark">user_phoneNumber</h3>{{user.user_phone_number}}
      <h3 class="table-dark">팔로잉 cnt</h3>
      <h3 class="table-dark">팔로워 cnt</h3>
    </div>
    <router-link to="/updateUser"><button>회원 정보 수정</button></router-link>
    <router-link to="/updatePass"><button>비밀번호 변경</button></router-link>
    <router-link to="/likeVideoList"><button>좋아요 영상 목록</button></router-link>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "MyPage",

  data() {
    return {
    }
  },

  computed: {
    ...mapState([
      'user',
    ])
  },

  methods: {

  },

}
</script>

<style scoped>
#profileImg {
  width: 30%;
  height: 0 auto;
}
</style>