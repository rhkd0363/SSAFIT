<template>  
  <div>
    <b-card style=" display: flex; justify-content: center;" class="mb-2">
      <b-card-header style="background-color: white;">
        <div style="display: flex;">
            <strong style="font-size: 25px;">{{board.title}}</strong>
        </div>
        <br>
        <div style="display: flex; justify-content: space-between;">
          <div style="display: flex; width: 80%; ">
            <img :src="board.user_img" style="width:35px; border-radius: 100%; background-color: #c9c1c1; align-self: center;">
            <div style="display: flex; flex-direction: column; align-items: baseline; margin-left: 1%;">
              <strong>{{ board.user_name }}</strong>
              <small style="font-size: 3px;">{{ board.reg_date }}</small>
            </div>
          </div>
          <strong>조회수 {{ board.view_cnt }}회</strong>
        </div>
      </b-card-header>
      <!-- 내용 -->
      <b-card-body>
        <b-card-text>
          <div style="display: flex;">
            <textarea class="form-control" v-model="board.content" ref="board_content" id="board_content" style="background-color: white; border:0" :disabled="updateCheck == false" rows="5"></textarea>
         </div>
        </b-card-text>
      </b-card-body>
      <!-- 버튼 -->
      <b-card-footer style="background-color: white;">
        <router-link to="/community/board"><b-button variant="info">목록</b-button></router-link>&nbsp;&nbsp;
        <b-button variant="primary" @click="moveUpdate" v-if="board.user_id == user.user_id">수정</b-button>&nbsp;&nbsp;
        <b-button variant="danger" @click="deleteBoard" v-if="board.user_id == user.user_id">삭제</b-button>
      </b-card-footer>
    </b-card>

      <!-- <div style="display: flex; margin-right: 1%;">
        <b-button @click="modifyReply" v-if="reply.user_id == user.user_id & updateCheck==false" variant="outline-primary" style="font-size: 5px; min-width: 50px; margin-right: 2%; height: 25pt;">수정</b-button>
        <b-button @click="deleteReply" v-if="reply.user_id == user.user_id & updateCheck==false" variant="outline-danger" style="font-size: 5px; min-width: 50px;margin-right: 2%; height: 25pt;">삭제</b-button>
        <b-button @click="updateReply" v-if="reply.user_id == user.user_id & updateCheck==true" variant="outline-primary" style="font-size: 5px; min-width: 50px;margin-right: 2%; height: 25pt;">완료</b-button>
        <b-button @click="cancleModify" v-if="reply.user_id == user.user_id & updateCheck==true" variant="outline-secondary" style="font-size: 5px; min-width: 50px;margin-right: 2%; height: 25pt;">취소</b-button>
      </div> -->

  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'BoardDetail',

  data() {
    return {
      updateCheck: false,
    }
  },

  computed: {
    ...mapState(['board','user']),
  },

  created() {
    const pathName = new URL(document.location).pathname.split('/');
    const id = pathName[pathName.length - 1];
    this.$store.dispatch('getBoard', id);
  },

  methods: {
    moveUpdate() {
      this.updateCheck = true;

      // setTimeout(() => {
      //   this.$refs.board_content.focus()
      // }, 0.1);
      this.$router.push({ name: 'boardUpdate' });
    },

    deleteBoard() {
      this.$store.dispatch('deleteBoard', this.board.id);
    },
  },
};
</script>

<style></style>