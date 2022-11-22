<template>
    <b-modal id="modal-write" title="게시글 작성" size="xl">
      <b-form >
        <b-form-group id="input-group-1" label="Title :" label-for="input-1">
          <b-form-input id="input-1" v-model="title" type="text" placeholder="Enter Title" required ref="title"></b-form-input>
          <small style="color: red;">{{titleMsg}}</small>
        </b-form-group>
        
        <b-form-group id="input-group-2" label="Content :" label-for="input-2">
          <b-textarea id="input-2" v-model="content" placeholder="Enter Content" rows="10" required ref="content"></b-textarea>
          <small style="color: red;">{{contentMsg}}</small>
        </b-form-group>
      </b-form>

      <template #modal-footer="{ }">
        <b-button variant="success" @click="createBoard">
          등록
        </b-button>
        <b-button variant="danger" @click="closeModal">
          취소
        </b-button>
      </template>
  </b-modal>
</template>

<script>

export default {
  name: "BoardCreate",
  data() {
    return {
      title: "",
      titleMsg: null,
      writer: "",
      content: "",
      contentMsg: null,
    };
  },
  methods: {
    createBoard() {
      console.log(this.title.trim().length);
      if(this.title.trim().length == 0){
        this.titleMsg = "제목을 작성해주세요."
        this.$refs.title.focus()
        return
      }
      
      if(this.content.trim().length == 0){
        this.contentMsg = "내용을 작성해주세요."
        this.$refs.content.focus()
        return
      }
      
      let board = {
        title: this.title,
        user_id : this.$store.state.user.user_id,
        content: this.content
      };

      this.title = '';
      this.content='';
      this.titleMsg='';
      this.contentMsg='';

      this.$bvModal.hide('modal-write')

      this.$store.dispatch("createBoard", board);

    },
    closeModal(){
      this.title = '';
      this.content='';
      this.titleMsg='';
      this.contentMsg='';

      this.$bvModal.hide('modal-write')
    }
  }
};
</script>

<style></style>
