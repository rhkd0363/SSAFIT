<template>
  <div style="margin: 2%;">
    <b-input-group style="width: 65%; margin: 1% 15%;">
      <b-select v-model="mode"  style="width: 20%;">
        <option value="1">제목</option>
        <option value="2">내용</option>
        <option value="3">제목+내용</option>
      </b-select>
      <b-input  type="text" v-model="keyword"  style="width: 70%;" @keyup.enter="search" placeholder="Enter Name"/>
      <b-button variant="info" style="width: 10%;" @click="search">검 색</b-button>
    </b-input-group>

    <div style="text-align: right;">
      <b-button v-b-modal.modal-write variant="primary" style="margin: 0 1% 0.5% 0; width: 8%;">글 작성</b-button>
    </div>
    
    <div class="card" style="border-left: 0; border-right: 0;">
        <table class="table align-items-center mb-0">
          <thead>
            <tr style="text-align: center;">
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2" colspan="1">번호</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" colspan="6" >제목</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" colspan="2">글쓴이</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" colspan="1" >조회수</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" colspan="2" >등록일</th>
            </tr>
          </thead>
          <tr v-for="(board,index) in boards.slice((currentPage-1)*perPage,(currentPage-1)*perPage+perPage)" :key="board.id">
            <td colspan="1">{{ index + (15*(currentPage-1)) +1 }}</td>
            <td colspan="6">
              <router-link :to="`/community/board/${board.id}`">{{ board.title }}</router-link>
            </td>
            <td colspan="2">{{ board.user_name }}</td>
            <td colspan="1">{{ board.view_cnt }}</td>
            <td colspan="2">{{ board.reg_date }}</td>
          </tr>
        </table>
    </div>
    <div class="overflow-auto" style="margin: 2%; display: flex; justify-content: center;">
      <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="my-table">
      </b-pagination>
    </div>

    <board-create></board-create>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import BoardCreate from './BoardCreate.vue';

export default {
  name: 'BoardList',
  components:{
    BoardCreate,
  },
  data() {
    return {
      mode: 1,
      keyword: '',
      perPage: 15,
      currentPage : 1,
    };
  },
  computed: {
    ...mapState(['boards']),
    rows(){
      return this.boards.length
    }
  },
  created() {
    this.$store.dispatch('getBoards');
  },
  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch('getBoards', payload);
    },
  },
};
</script>

<style></style>
