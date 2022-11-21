<template>
  <div class="loginForm">
    <br>
    <h2>LOGIN</h2>
    <hr>
    <b-form @submit.prevent="loginUser">
      <b-form-group id="id-group" label="ID" label-for="id">
        <b-form-input id="id" v-model="id" placeholder="ID를 입력해주세요." ref="id">
        </b-form-input>
        <small style="margin-left: 10px; color: red;" v-if="id.length === 0">{{ idMsg }}</small>
      </b-form-group>
      <b-form-group id="pw-group" label="PASSWORD" label-for="pw">
        <b-form-input type="password" id="pw" v-model="pw" placeholder="영문, 숫자, 특수문자를 조합하여 입력해주세요.(8-16자)" ref="pw">
        </b-form-input>  
        <small style="margin-left: 10px; color: red;" v-if="pw.length === 0" >{{ pwMsg }}</small>
      </b-form-group>
      <div style="text-align: center;">
       <b-button type="submit" variant="primary">LOGIN</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
export default {
    name: "LoginPage",

    data() {
        return {
            id: "",
            pw: "",
            idMsg: null,
            pwMsg: null,
        }
    },

    created() {
      // 로그인 정보가 존재할 시 로그인 화면으로 못 오게 설정
      if(this.$store.state.user != null){
        this.$router.push('/')
      }
    },

    methods: {
      loginUser() {
          // 유효성 검사
          //11/21 ( alert 메시지 출력에서 -> ID,PW input 태그 밑에 글씨 출력으로 변경 & prevent 이벤트로 화면 리로드 되는거 막음)
          if(this.id.length === 0){
          this.idMsg = "아이디를 입력해주세요.";
          this.$refs.id.focus();
          return ;
          }

          if(this.pw.length === 0){
          this.pwMsg = "비밀번호를 입력해주세요.";
          this.$refs.pw.focus();
          return ;
          }

          // 입력 받은 객체 유효성 검사를 한 후 배열에 추가
          let loginInfo = {
            user_id: this.id,
            user_pw: this.pw,
          };

          this.$store.dispatch("loginUser", loginInfo)
      }
    },
}
</script>

<style>
.loginForm{
  margin-left: 20%;
  margin-right: 20%;
}
</style>