<template>
   <b-modal id="trekking-detail" title="코스 상세" size="xl">
    <b-list-group>
        <b-list-group-item>
            <div><div><strong>코스명</strong></div>{{trekkingCourse.wlk_cours_flag_nm}} ( {{ trekkingCourse.wlk_cours_nm}} ) </div>
            <div><div><strong>주소</strong></div>{{trekkingCourse.lnm_addr}} </div>
        </b-list-group-item>
        <b-list-group-item>
            <div>
                <div><strong>코스 설명 ( 난이도 : {{trekkingCourse.cours_level_nm}} , 코스 경로 길이 : {{trekkingCourse.cours_detail_lt_cn}} Km , 
                    예상 소요 시간 : {{trekkingCourse.cours_time_cn}})</strong></div>
                {{trekkingCourse.cours_dc}}
            </div>
        </b-list-group-item>
        <b-list-group-item>
            <div>
                <div>
                <strong>코스 추가 설명</strong>
                </div>
                {{trekkingCourse.adit_dc}}
            </div>
        </b-list-group-item>
        <div style="display: flex; width: 100%;">
            <b-list-group-item style="width: 50%; border-top: 0; border-bottom: 0; ">
                <div>
                    <div>
                    <strong>화장실</strong>
                    </div>
                    {{trekkingCourse.toilet_dc}}
                </div>
            </b-list-group-item>
            <b-list-group-item style="width: 50%; border-left: 0; border-bottom: 0;">
                <div>
                    <div>
                    <strong>편의시설</strong>
                    </div>
                    {{trekkingCourse.cvntl_nm}}
                </div>
            </b-list-group-item>
        </div>
        <b-list-group-item>
            <div>
                <div>
                <strong>부가 설명</strong>
                </div>
                {{trekkingCourse.optn_dc}}
            </div>
        </b-list-group-item>
    </b-list-group>

      <template #modal-footer="{ ok }">
        <b-button variant="info" @click="ok()" style="width: 10%; margin-right: 45%;">
          확인
        </b-button>
      </template>
  </b-modal>

  <!--                                           esntl_id: trekkingCourse.esntl_id         , //코스 ID
        trekkingCourse.wlk_cours_flag_nm                , //코스명
        trekkingCourse.wlk_cours_nm             , //코스 세부명
        trekkingCourse.cours_dc             , //코스 설명
        trekkingCourse.cours_level_nm                   , // 코스 난이도
        trekkingCourse.cours_lt_cn              , //코스 경로 길이
        trekkingCourse.cours_detail_lt_cn               , //코스 경로 상세 길이 km단위임
        trekkingCourse.adit_dc              , //코스 추가 설명
        trekkingCourse.cours_time_cn                , //예상소요시간
        trekkingCourse.optn_dc              , // 부가적인 설명
        trekkingCourse.toilet_dc                , //화장실
        trekkingCourse.cvntl_nm             , //편의시설
        trekkingCourse.lnm_addr                 , // 주소 -->
</template>

<script>
import { mapState } from 'vuex'
export default {
    name: 'TrekkingDetail',
    computed:{
        ...mapState(['trekkingCourse'])
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