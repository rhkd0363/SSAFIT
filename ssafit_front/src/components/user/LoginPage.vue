<template>
    <div class="container">
        <h2>LOG IN</h2>
        <fieldset class="text-center">
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" class="view" v-model="id" ref="id"/><br/>
            <label for="pw">비밀번호</label>
            <input type="password" id="pw" name="pw" class="view" v-model="pw" ref="pw"/><br/>
            <button type="button" class="btn btn-warning" @click="loginUser">Log In</button>
        </fieldset>
    </div>
</template>

<script>
export default {
    name: "LoginPage",

    data() {
        return {
            id: "",
            pw: "",
        }
    },

    methods: {
      loginUser() {
          // 유효성 검사
          if(this.id.length === 0){
          alert("아이디를 입력해주세요.");
          this.$refs.id.focus();
          return;
          }

          if(this.pw.length === 0){
          alert("비밀번호를 입력해주세요.");
          this.$refs.pw.focus();
          return;
          }

          // 입력 받은 객체 유효성 검사를 한 후 배열에 추가
          let loginInfo = {
            user_id: this.id,
            user_pw: this.pw,
          };

          this.$store.dispatch("loginUser", loginInfo)
      }
    }
}
</script>

<style>
a {
  text-decoration: none;
}

.text-center {
  text-align: center;
}

.main {
    text-align: center;
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