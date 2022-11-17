<template>
    <div class="container">
        <h2>SIGN UP</h2>
        <fieldset class="text-center">
            <label for="old_pass">기존 비밀번호</label>
            <input type="password" id="old_pass" name="old_pass" class="view" v-model="old_pass" ref="old_pass"/>
            <button @click="checkPass">비밀번호 확인</button><br/>
            <label for="pw">비밀번호</label>
            <input type="password" id="pw" name="pw" class="view" v-model="pw" ref="pw" placeholder="영문, 숫자, 특수문자를 조합하여 입력해주세요(8-16자)" :disabled="old_check"/><br/>
            <label for="pwCheck">비밀번호 확인</label>
            <input type="password" id="pwCheck" name="pwCheck" class="view" v-model="pwCheck" ref="pwCheck" placeholder="영문, 숫자, 특수문자를 조합하여 입력해주세요(8-16자)" :disabled="old_check"/><br/>
            {{vaildPassword}}<br>
            <button type="button" class="btn btn-warning" @click="updatePass" :disabled="old_check">Join</button>
        </fieldset>
    </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';
export default {
    name: "UpdatePass",

    data() {
        return {
            old_pass: "",
            old_check: true,
            pw: "",
            pwCheck: "",
        }
    },
    computed:{
      ...mapState(['user']),
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
    },

    methods: {
      updatePass() {
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

          // 입력 받은 객체 유효성 검사를 한 후 배열에 추가
          let userInfo = {
            user_id: this.user.user_id,
            user_pw: this.pw
          };

          axios({
            url: process.env.VUE_APP_REST_URL + '/user/updatePass',
            method: "PUT",
            params: userInfo,
             headers: {
              "access-token": sessionStorage.getItem("access-token")
            }
          })
            .then((res) => {
              if (res.data === "success") {
                alert("비밀번호 변경이 완료되었습니다.");
                this.$router.push('/myPage')
              }
              else {
                alert("비밀번호가 변경 실패");
                this.old_check = true
              }
            })
      },

      checkPass(){
          axios({
            url: process.env.VUE_APP_REST_URL + '/user/passCheck',
            method: "GET",
            params: {
              user_id: this.user.user_id,
              user_pw: this.old_pass
            },
             headers: {
              "access-token": sessionStorage.getItem("access-token")
            }
          })
            .then((res) => {
              if (res.data === "success") {
                alert("확인되었습니다.");
                this.old_check = false
              }
              else {
                alert("비밀번호가 틀렸습니다.");
                this.old_check = true
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