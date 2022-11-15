<template>
    <div class="container">
        <h2>회원 가입</h2>
        <fieldset class="text-center">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" class="view" v-model="name" ref="name"/><br/>
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" class="view" v-model="id" ref="id"/><br/>
            <label for="pw">비밀번호</label>
            <input type="password" id="pw" name="pw" class="view" v-model="pw" ref="pw"/><br/>
            <label for="pwCheck">비밀번호 확인</label>
            <input type="password" id="pwCheck" name="pwCheck" class="view" v-model="pwCheck" ref="pwCheck"/><br/>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" class="view" v-model="email" ref="email"/><br/>
            <label for="phoneNumber">전화번호</label>
            <input type="text" id="phoneNumber" name="phoneNumber" class="view" v-model="phoneNumber" ref="phoneNumber"/><br/>
            <button type="button" class="btn btn-warning" @click="joinUser">가입</button>
        </fieldset>
    </div>
</template>

<script>
export default {
    name: "Join",

    data() {
        return {
            name: "",
            id: "",
            pw: "",
            pw2: "",
            email: "",
            phoneNumber: "",
        }
    },

    methods: {
      createMovie() {
          // 유효성 검사
          if(this.name.length === 0){
          alert("이름을 입력해주세요.");
          this.$refs.name.focus();
          return;
          }

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

          if(this.pw2.length === 0){
          alert("비밀번호를 확인해주세요.");
          this.$refs.pw2.focus();
          return;
          }

          if(this.email.length === 0){
          alert("이메일을 입력해주세요.");
          this.$refs.email.focus();
          return;
          }

          if(this.phoneNumber.length === 0){
          alert("전화번호를 확인해주세요.");
          this.$refs.phoneNumber.focus();
          return;
          }

          // 입력 받은 객체 유효성 검사를 한 후 배열에 추가
          let userInfo = {
            name: this.name,
            id: this.id,
            pw: this.pw,
            pw2: this.pw2,
            email: this.email,
            phoneNumber: this.phoneNumber,
          };

          this.$emit("join-user", userInfo)
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