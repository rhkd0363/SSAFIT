<template>
    <div class="container">
        <h2>SIGN UP</h2>
        <fieldset class="text-center">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" class="view" v-model="name" ref="name"/><br/>
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" class="view" v-model="id" ref="id"/>
            <button @click="idCheck">아이디 중복확인</button><br/>
            <label for="pw">비밀번호</label>
            <input type="password" id="pw" name="pw" class="view" v-model="pw" ref="pw" placeholder="영문, 숫자, 특수문자를 조합하여 입력해주세요(8-16자)"/><br/>
            <label for="pwCheck">비밀번호 확인</label>
            <input type="password" id="pwCheck" name="pwCheck" class="view" v-model="pwCheck" ref="pwCheck" placeholder="영문, 숫자, 특수문자를 조합하여 입력해주세요(8-16자)"/><br/>
            {{vaildPassword}}<br>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" class="view" v-model="email" ref="email" placeholder="abc@gmail.com"/><br/>
            {{vaildEmail}}<br>
            <label for="phoneNumber">전화번호</label>
            <input type="text" id="phoneNumber" name="phoneNumber" class="view" v-model="phoneNumber" ref="phoneNumber"/><br/>
            <!-- <label for="img">프로필 사진</label>
            <input type="text" id="img" name="img" class="view" v-model="img" ref="img"/><br/> -->
            <button type="button" class="btn btn-warning" @click="joinUser" :disabled="joinCheck">Join</button>
        </fieldset>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: "JoinPage",

    data() {
        return {
            name: "",
            id: "",
            pw: "",
            pwCheck: "",
            email: "",
            phoneNumber: "",
            joinCheck: true,
        }
    },
    computed:{
      vaildPassword(){
        const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
          
          if (!validatePassword.test(this.pw) || !this.pw) {
              return "영문, 숫자, 특수문자를 조합하여 입력해주세요(8-16자)"
          }
          
          if(this.pw != this.pwCheck){
            return "비밀번호가 일치하지 않습니다."
          }

          return "비밀번호가 일치합니다."
      },
      vaildEmail(){
        const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/

          if (!validateEmail.test(this.email) || !this.email) {
              return "이메일 주소를 정확히 입력해주세요."
          }

          return null
      }
    },

    methods: {
      joinUser() {
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

          const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
          
          if (!validatePassword.test(this.pw) || !this.pw) {
              alert("영문, 숫자, 특수문자를 조합하여 입력해주세요(8-16자)");
              this.$refs.email.focus();
              return
          }

          if(this.pw != this.pwCheck){
            alert("비밀번호가 일치하지 않습니다.");
            this.$refs.pwCheck.focus();
            return;
          }

          const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/

          if (!validateEmail.test(this.email) || !this.email) {
              alert("이메일 주소를 정확히 입력해주세요.");
              this.$refs.email.focus();
              return
          }

          if(this.phoneNumber.length === 0){
          alert("전화번호를 확인해주세요.");
          this.$refs.phoneNumber.focus();
          return;
          }

          // 입력 받은 객체 유효성 검사를 한 후 배열에 추가
          let userInfo = {
            user_name: this.name,
            user_id: this.id,
            user_pw: this.pw,
            user_email: this.email,
            user_phone_number: this.phoneNumber,
          };

          this.$store.dispatch("joinUser", userInfo);  
      },

      idCheck(){
      axios({
        url: process.env.VUE_APP_REST_URL + '/user/idcheck',
        method: "GET",
        params: {
          user_id: this.id
        }
      })
        .then((res) => {
          if (res.data === "success") {
            alert("사용 가능한 아이디입니다");
            this.joinCheck = false
          }
          else {
            alert("중복된 아이디입니다");
          }
        })
      },
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