<template>
   <b-modal id="trekking-detail" title="코스 상세" size="xl">
    <b-list-group>
        <b-list-group-item>
            <div><div><strong>해수욕장명</strong></div>{{surfingPlace.beachName}}</div>
            <div><div><strong>위치</strong></div>{{surfingPlace.sigungu}} </div>
        </b-list-group-item>
        <b-list-group-item>
            <div>
                <div><strong>해수욕장 면적</strong></div>
                <div>
                    길이 : {{surfingPlace.beach_len}}m, 넓이 : {{surfingPlace.beach_wid}}m
                </div>
                면적 : {{surfingPlace.beach_len * surfingPlace.beach_wid}}㎡
            </div>
        </b-list-group-item>
        <b-list-group-item>
            <div>
                <div>
                <strong>관련 사이트</strong>
                </div>
                관련 사이트 이름 : {{surfingPlace.link_nm}}<br>
                관련 사이트 연락처 : {{surfingPlace.link_tel }}<br>
                사이트 주소 : <a :href="`${surfingPlace.link_addr}`">{{surfingPlace.link_addr}}}</a>
            </div>
        </b-list-group-item>
    </b-list-group>

      <template #modal-footer="{ ok }">
        <b-button variant="info" @click="ok()" style="width: 10%; margin-right: 45%;">
          확인
        </b-button>
      </template>
  </b-modal>
</template>

<script>
import { mapState } from 'vuex'
export default {
    name: 'SurfingDetail',
    computed:{
        ...mapState(['surfingPlace'])
    }
}
</script>

<style scoped>
strong{
    font-size: 20px;
}
div{
    margin-bottom: 0.5%;
}
.list-group-item{
    margin-bottom: 0;
}
</style>