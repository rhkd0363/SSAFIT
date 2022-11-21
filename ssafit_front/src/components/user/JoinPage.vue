<template>
  <div class="joinForm">
    <img src="@/assets/SINGUP_BANNER.jpg" style="margin-top: 1%; border-radius: 1%; z-index: -1; opacity: 80%; position: absolute; width: 70%;">
    <!-- <video autoplay loop muted src="@/assets/SingUPBanner.mp4" style="z-index: -1; opacity: 100%; position: absolute; width: 80%;"></video> -->
    <br>
    <div style="background-color: rgba(255,255,255,0.8); width: 70%; padding: 2%; margin-top: 5%;">
      <h2>SIGN UP</h2>
      <hr>
      <b-form @submit.prevent="joinUser">
        <b-form-group id="name-group" label="NAME" label-for="name">
          <b-form-input id="name" v-model="name" placeholder="Enter Your Name!" ref="name"></b-form-input>
      </b-form-group>
      <b-form-group id="id-group" label="ID" label-for="id">
        <div style="display: flex;">
          <b-form-input id="id" v-model="id" placeholder="Enter Your ID!" ref="id"></b-form-input>  
          <b-button variant="outline-success" @click="idCheck" style="width: 160px;">VALID CHECK</b-button>
        </div>
        <small style="margin-left: 10px; color: red;" v-if="joinCheck" >{{idCheckMsg}}</small>
        <small style="margin-left: 10px; color: green;" v-else >{{idCheckMsg}}</small>
      </b-form-group>
      <b-form-group id="pw-group" label="PASSWORD" label-for="pw">
        <b-form-input type="password" id="pw" v-model="pw" placeholder="Enter Your Password!" ref="pw">
        </b-form-input>  
      </b-form-group>
      <b-form-group id="pwCheck-group" label="CONFIRM" label-for="pwCheck">
        <b-form-input type="password" id="pwCheck" v-model="pwCheck" placeholder="Enter Your Password Again!" ref="pwCheck">
        </b-form-input>  
        <small style="margin-left: 10px; color: red;" v-if="pw != pwCheck">{{vaildPassword}}</small>
        <small style="margin-left: 10px; color: green;" v-else>{{vaildPassword}}</small>
      </b-form-group>
      <b-form-group id="email-group" label="EMAIL" label-for="email">
        <b-form-input id="email" v-model="email" placeholder="ssafit@ssafy.com" ref="email">
        </b-form-input>  
        <small style="margin-left: 10px; color: red;" v-if="vaildEmail != '✔ 이메일 형식이 일치합니다.'" >{{vaildEmail}}</small>
        <small style="margin-left: 10px; color: green;" v-else >{{vaildEmail}}</small>
      </b-form-group>
      <b-form-group id="phoneNumber-group" label="PHONE-NUMBER" label-for="phoneNumber">
        <b-form-input id="phoneNumber" v-model="phoneNumber" placeholder="Enter Your Phone Number!" ref="phoneNumber">
        </b-form-input>
      </b-form-group>
      
      <div style="text-align: center;">
        <b-button type="submit" variant="primary" :disabled="joinCheck">SIGN UP</b-button>
      </div>
    </b-form>
  </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "JoinPage",

  data() {
    return {
      name: "",
      id: "",
      pw: '',
      pwCheck: null,
      email: "",
      phoneNumber: "",
      joinCheck: true,
      img: "",
      showImg: false,
      idCheckMsg: "ID 중복확인이 필요합니다. ",
    };
  },

  computed: {
    vaildPassword() {
      const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

      if (!validatePassword.test(this.pw) || !this.pw) {
        return "✘ 영문, 숫자, 특수문자를 조합하여 입력해주세요(8-16자)";
      }

      if (this.pw != this.pwCheck) {
        return "✘ 비밀번호가 일치하지 않습니다.";
      }

      return "✔ 비밀번호가 일치합니다.";
    },

    vaildEmail() {
      const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;

      if (!validateEmail.test(this.email) || !this.email) {
        return "✘ 이메일 주소를 정확히 입력해주세요.";
      }

      return "✔ 이메일 형식이 일치합니다.";
    }
  },

  methods: {
    // 회원가입 하기
    joinUser() {
      // 유효성 검사
      if (this.name.length === 0) {
        alert("이름을 입력해주세요.");
        this.$refs.name.focus();
        return;
      }

      if (this.id.length === 0) {
        alert("아이디를 입력해주세요.");
        this.$refs.id.focus();
        return;
      }

      const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

      if (!validatePassword.test(this.pw) || !this.pw) {
        alert("영문, 숫자, 특수문자를 조합하여 입력해주세요(8-16자)");
        this.$refs.email.focus();
        return;
      }

      if (this.pw != this.pwCheck) {
        alert("비밀번호가 일치하지 않습니다.");
        this.$refs.pwCheck.focus();
        return;
      }

      const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;

      if (!validateEmail.test(this.email) || !this.email) {
        alert("이메일 주소를 정확히 입력해주세요.");
        this.$refs.email.focus();
        return;
      }

      if (this.phoneNumber.length === 0) {
        alert("전화번호를 입력해주세요.");
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
        user_img: this.img
      };

      this.$store.dispatch("joinUser", userInfo);

      this.$router.push('/')
    },

    // 아이디 중복 체크하기
    idCheck() {
      if(this.id.length == 0 | this.id.trim().length == 0){
        this.idCheckMsg="✘ 정확한 ID를 입력해주세요";
        return;
      }

      axios({
        url: process.env.VUE_APP_REST_URL + "/user/idcheck",
        method: "GET",
        params: {
          user_id: this.id
        }
      }).then(res => {
        if (res.data === "success") {
          this.idCheckMsg="✔ 사용 가능한 ID입니다";
          this.joinCheck = false;
          this.img =
            "https://avatars.dicebear.com/api/big-smile/" + this.id + ".svg";
        } else {
          this.joinCheck = true;
          this.idCheckMsg="✘ 이미 사용중인 ID입니다.";
        }
      });
    }

    // // 자동 프로필 생성하기
    // createImg() {
    //   this.showImg = true;
    //   this.img = "https://avatars.dicebear.com/api/big-smile/" + this.id + ".svg"
    // },
  }
};
</script>

<style scoped>
.joinForm {
  margin-left: 10%;
  margin-right: 10%;
  display: flex;
  justify-content: center;
}
</style>