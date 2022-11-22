<template>
  <div style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
    <h1>트레킹 코스 검색</h1>
    <b-input-group style="width: 50%; margin: 1%;">
      <b-select id="difficulty" v-model="difficulty" style="width: 5%;">
        <option value="null">전체</option>
        <option value="매우쉬움">매우쉬움</option>
        <option value="쉬움">쉬움</option>
        <option value="보통">보통</option>
        <option value="어려움">어려움</option>
        <option value="매우어려움">매우어려움</option>
      </b-select>
      <b-select id="distance" v-model="distance" style="width: 5%;">
        <option value="null">전체</option>
        <option value="1Km미만">1Km미만</option>
        <option value="1~5Km미만">1~5Km미만</option>
        <option value="5~10Km미만">5~10Km미만</option>
        <option value="10~15Km미만">10~15Km미만</option>
        <option value="15~20Km미만">15~20Km미만</option>
        <option value="20~100Km미만">20~100Km미만</option>
        <option value="100Km이상">100Km이상</option>
      </b-select>
      <b-input
        type="text"
        v-model="searchKeyword"
        @keyup.enter="searchTrekkingCourse"
        style="width: 45%;"
      />
      <b-button variant="info" style="width: 10%;" @click="searchTrekkingCourse">검 색</b-button>
    </b-input-group>

    <div style="display: flex; justify-content: center; width: 100%;">
      <div id="map"></div>

      <div
        class="courseInfo"
        style="height: 700px; width: 300px; overflow-y: scroll;background-color: rgba(255, 193, 7, 0.07);"
        :hidden="markCheck"
      >
        <b-list-group v-for="point in markerPoint" :key="point">
          <b-list-group-item
            v-if="point.latlng.La == trekkingPoint.La & point.latlng.Ma == trekkingPoint.Ma"
            style="padding: 0;"
          >
            <b-button variant="light" style="width: 100%; padding: 0;" @click="showModal(point)">
              <div>
                <small>코스명 : {{point.wlk_cours_flag_nm}}</small>
              </div>
              <div>
                <small>상세명 : {{point.wlk_cours_nm}}</small>
              </div>
              <div>
                <small>난이도 : {{point.cours_level_nm}}</small>
              </div>
              <div>
                <small>코스 길이 : {{point.cours_lt_cn}}</small>
              </div>
              <div>
                <small>상세 길이: {{point.cours_detail_lt_cn}}Km</small>
              </div>
              <div>
                <small>예상 소요 시간 : {{point.cours_time_cn}}</small>
              </div>
              <div>
                <small>주소 : {{point.lnm_addr}}</small>
              </div>
            </b-button>
          </b-list-group-item>
        </b-list-group>
      </div>
      <b-button variant="secondary" :hidden="markCheck" @click="markCheck = true">닫기</b-button>
    </div>

    <trekking-detail></trekking-detail>
  </div>
</template>
    
    <script>
import { mapState } from "vuex";
import axios from "axios";
import TrekkingDetail from "./TrekkingDetail.vue"

export default {
  name: 'TrekkingHome',
  data() {
    return {
      distance: null,
      difficulty: null,
      searchKeyword: "",
      map: null,
      trekkingCourses: [],
      // 화면에 표시되어있는 marker들
      markers: [],
      trekkingPoint: "",
      markCheck: true
    };
  },
  components:{
    TrekkingDetail,
  },
  computed: {
    ...mapState(["trekkingCourses"]),
    markerPoint() {
      return this.trekkingCourses.map(trekkingCourse => ({
        esntl_id: trekkingCourse.esntl_id, //코스 ID
        wlk_cours_flag_nm: trekkingCourse.wlk_cours_flag_nm, //코스명
        wlk_cours_nm: trekkingCourse.wlk_cours_nm, //코스 세부명
        cours_dc: trekkingCourse.cours_dc, //코스 설명
        cours_level_nm: trekkingCourse.cours_level_nm, // 코스 난이도
        cours_lt_cn: trekkingCourse.cours_lt_cn, //코스 경로 길이
        cours_detail_lt_cn: trekkingCourse.cours_detail_lt_cn, //코스 경로 상세 길이 km단위임
        adit_dc: trekkingCourse.adit_dc, //코스 추가 설명
        cours_time_cn: trekkingCourse.cours_time_cn, //예상소요시간
        optn_dc: trekkingCourse.optn_dc, // 부가적인 설명
        toilet_dc: trekkingCourse.toilet_dc, //화장실
        cvntl_nm: trekkingCourse.cvntl_nm, //편의시설
        lnm_addr: trekkingCourse.lnm_addr, // 주소
        latlng: new kakao.maps.LatLng(
          trekkingCourse.cours_spot_la,
          trekkingCourse.cours_spot_lo
        ) // 경, 위도
      }));
    }
  },
  methods: {
    showModal(point) {
      console.log(point)
      this.$store.state.trekkingCourse = point;

      this.$bvModal.show('trekking-detail')
    },
    searchTrekkingCourse() {
      axios({
        url: process.env.VUE_APP_REST_URL + "/trekking",
        method: "GET",
        params: {
          searchKeyword: this.searchKeyword,
          difficulty: this.difficulty,
          distance: this.distance
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        }
      })
        .then(res => {
          this.trekkingCourses = res.data;

          setTimeout(() => {
            this.displayMarkers(this.markerPoint);
          }, 500);
        })
        .catch(err => {
          console.log(err);
        });
    },

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(36.3504119, 127.3845475),
        level: 9
      };
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarkers(positions) {
      // 1. 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      if (this.markers.length > 0) {
        this.markers.forEach(item => {
          item.setMap(null);
        });
      }

      // 2. 마커 이미지 커스터마이징 하기
      // javascript 영역에서 assets의 정보 가져오기
      // const imgSrc = require("@/assets/son.jpg");
      // const imgSize = new kakao.maps.Size(24, 35);
      // const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 3. 마커 표시하기
      positions.forEach(position => {
        // information window 생성
        const infowindow = new kakao.maps.InfoWindow({
          removable: true,
          content:
            `<div style="width:300px;"><div style="padding:2px;">코스명 : ${position.wlk_cours_flag_nm}</div>` +
            //  `<div style="padding:2px;">상세명 : ${position.wlk_cours_nm}</div>` +
            //  `<div style="padding:2px;">난이도 : ${position.cours_level_nm}</div>` +
            //  `<div style="padding:2px;">코스 길이 : ${position.cours_lt_cn}</div>` +
            //  `<div style="padding:2px;">상세 : ${position.cours_detail_lt_cn}</div>` +
            //  `<div style="padding:2px;">예상 소요 시간 : ${position.cours_time_cn}</div>` +
            `<div style="padding:2px;">주소 : ${position.lnm_addr}</div></div>`
        });

        // wlk_cours_flag_nm : trekkingCourse.wlk_cours_flag_nm, 코스명
        // wlk_cours_nm : trekkingCourse.wlk_cours_nm, 코스 상세 명
        // cours_level_nm 코스 난이도
        // cours_lt_cn : trekkingCourse.cours_lt_cn,  코스 길이
        // cours_detail_lt_cn : trekkingCourse.cours_detail_lt_cn, 코스 상세 길이
        // cours_time_cn : trekkingCourse.cours_time_cn,  코스 예상 소요 시간
        // lnm_addr : trekkingCourse.lnm_addr,  주소

        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng
        });
        // 이벤트 등록
        //  kakao.maps.event.addListener(marker, "mouseover", () => {infowindow.open(this.map, marker);});
        kakao.maps.event.addListener(marker, "click", () => {
          this.trekkingPoint = position.latlng;
          this.markCheck = false;
        });
        kakao.maps.event.addListener(marker, "mouseover", () => {
          infowindow.open(this.map, marker);
        });
        kakao.maps.event.addListener(marker, "mouseout", () => {
          infowindow.close(this.map, marker);
        });

        this.markers.push(marker);
      });

      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    }
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      //console.log("이미 로딩됨: ", window.kakao);
      this.initMap();
    }
  }
};
</script>
    
    <style scoped>
#map {
  width: 700px;
  height: 700px;
}
</style>


<style scoped>
.courseInfo {
  -ms-overflow-style: none;
}
.courseInfo::-webkit-scrollbar {
  display: none;
}
</style>