<template>
    <div class="container">
        <h2>EDIT USER INFO</h2>
        <fieldset class="text-center">
            <label for="name">NAME</label>
            <input type="text" id="name" name="name" class="view" v-model="user.user_name" ref="name"/><br/>
            <label for="id">ID</label>
            <input type="text" id="id" name="id" class="view" v-model="user.user_id" ref="id" disabled/>
            <label for="email">EMAIL</label>
            <input type="text" id="email" name="email" class="view" v-model="user.user_email" ref="email" placeholder="abc@gmail.com"/><br/>
            {{vaildEmail}}<br>
            <label for="phoneNumber">PHONE-NUMBER</label>
            <input type="text" id="phoneNumber" name="phoneNumber" class="view" v-model="user.user_phone_number" ref="phoneNumber"/><br/>
            <b-button type="button" class="btn btn-warning" @click="updateUser">EDIT</b-button>
        </fieldset>
    </div>
</template>

<script>
import { mapState } from 'vuex';
import axios from 'axios';
export default {
    name: "UpdateUser",
    computed:{
      ...mapState(['user']),
      vaildEmail(){
        const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/

          if (!validateEmail.test(this.user.user_email) || !this.user.user_email) {
              return "이메일 주소를 정확히 입력해주세요."
          }

          return null
      }
    },

    methods: {
      updateUser() {

        if(this.user.user_name.length === 0){
          alert("이름을 확인해주세요.");
          this.$refs.name.focus();
          return;
          }

          const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/

        if (!validateEmail.test(this.user.user_email) || !this.user.user_email) {
            alert("이메일 주소를 정확히 입력해주세요.");
            this.$refs.email.focus();
            return
          }         

          if(this.user.user_phone_number.length === 0){
          alert("전화번호를 확인해주세요.");
          this.$refs.phoneNumber.focus();
          return;
          }

          // 입력 받은 객체 유효성 검사를 한 후 배열에 추가
          let userInfo = {
            user_name: this.user.user_name,
            user_id: this.user.user_id,
            user_email: this.user.user_email,
            user_phone_number: this.user.user_phone_number,
          };

          axios({
            url: process.env.VUE_APP_REST_URL + '/user',
            method: "PUT",
            params: userInfo
          })
          .then((res) => {
            if(res.data == 'success'){
              alert('수정이 완료되었습니다.')
              this.$router.push('/')
            }else{
              alert('수정 실패')
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