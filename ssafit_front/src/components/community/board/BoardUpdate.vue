<template>
  <div>
    <div style="margin-top: 5%;">
      <b-card style=" display: flex; justify-content: center;" class="mb-2">
        <b-card-header style="background-color: white;">
          <div style="display: flex;">
              <b-input v-model="board.title" style="font-weight: bolder; font-size: 25px; "></b-input>
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
              <textarea class="form-control" v-model="board.content" style="background-color: white;" rows="5"></textarea>
           </div>
          </b-card-text>
        </b-card-body>
        <!-- 버튼 -->
        <b-card-footer style="background-color: white;">
          <b-button variant="primary" @click="updateBoard">완료</b-button>&nbsp;&nbsp;
          <b-button variant="danger" @click="updateCancel">취소</b-button>
        </b-card-footer>
      </b-card>
  
    </div>

    
    <!-- <fieldset>
      
      <textarea v-model="board.content" cols="30" rows="10"></textarea>
      <button @click="updateBoard">수정</button>
    </fieldset> -->
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'BoardUpdate',

  computed: {
    ...mapState(['board']),
  },

  methods: {
    updateBoard() {
      let updateBoard = {
        id: this.board.id,
        title: this.board.title,
        writer: this.board.writer,
        content: this.board.content,
      };
      this.$store.dispatch('updateBoard', updateBoard);
    },
    updateCancel(){
      this.$router.push({name: 'boardDetail', params: { id: this.board.id }})
    }
  },
};
</script>

<style></style>
