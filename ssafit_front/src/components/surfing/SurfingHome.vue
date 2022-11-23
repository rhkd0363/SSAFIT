<template>
  <div style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
    <h1 style="margin-top: 1%;">Surfing Place</h1>
    <b-input-group style="width: 50%; margin: 1%;">
      <b-input
        type="text"
        v-model="searchKeyword"
        @keyup.enter="searchSurfingPlace"
        style="width: 45%;"
        placeholder="서핑을 하고 싶은 지역명을 입력하세요!"
      />
      <b-button variant="info" style="width: 10%;" @click="searchSurfingPlace">검 색</b-button>
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
            v-if="point.latlng.La == surfingPoint.La & point.latlng.Ma == surfingPoint.Ma"
            style="padding: 0;"
          >
            <b-button variant="light" style="width: 100%; padding: 0;" @click="showModal(point)">
              <div>
                <small>해수욕장명 : {{point.beachName}}</small>
              </div>
              <div>
                <small>위치 : {{point.sigungu}}</small>
              </div>
              <div>
                <small>해수욕장 길이 : {{point.beach_len}}m</small>
              </div>
              <div>
                <small>해수욕장 폭 : {{point.beach_wid}}m</small>
              </div>
            </b-button>
          </b-list-group-item>
        </b-list-group>
      </div>
      <b-button variant="secondary" :hidden="markCheck" @click="markCheck = true">닫기</b-button>
    </div>
    <surfing-detail></surfing-detail>
  </div>
</template>
    
    <script>
import axios from "axios";
import SurfingDetail from "./SurfingDetail.vue";

export default {
  name: 'SurfingHome',
  components:{
    SurfingDetail,
  },
  data() {
    return {
      searchKeyword: "",
      map: null,
      surfingPlaces: [],
      // 화면에 표시되어있는 marker들
      markers: [],
      surfingPoint: "",
      markCheck: true
    };
  },

  computed: {
    markerPoint() {
      return this.surfingPlaces.map(surfingPlace => ({
        beachName: surfingPlace.sta_nm, //해수욕장 이름
        sigungu : surfingPlace.sido_nm+surfingPlace.gugun_nm, //시군구
        beach_len : surfingPlace.beach_len, //길이
        beach_wid : surfingPlace.beach_wid, // 폭
        beach_knd : surfingPlace.beach_knd,  // 특징
        link_addr : surfingPlace.link_addr, // 관련 사이트 url
        link_nm : surfingPlace.link_nm, // 관련 사이트 이름
        link_tel : surfingPlace.link_tel, // 관련 사이트 연락처
        beach_img : surfingPlace.beach_img,
        latlng: new kakao.maps.LatLng(
          surfingPlace.lat,
          surfingPlace.lon
        ) // 경, 위도
      }));
    }
  },
  methods: {
    showModal(point) {
      this.$store.state.surfingPlace = point;

      this.$bvModal.show('trekking-detail')
    },
    searchSurfingPlace() {
      axios({
        url: process.env.VUE_APP_SURF_PLACE_API_URL+
        '?ServiceKey='+process.env.VUE_APP_SURF_PLACE_KEY+
        '&numOfRows=20'+
        '&SIDO_NM='+this.searchKeyword+
        '&resultType=json'+
        '&SG_APIM='+VUE_APP_SURF_PLACE_SG_APIM_KEY,
        method: "GET" 
      })
        .then(res => {
          console.log(res);
          this.surfingPlaces = res.data.getOceansBeachInfo.item;

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
          `<div style="width:300px;"><div style="padding:2px;">해수욕장 명 : ${position.beachName}</div>` +
          //  `<div style="padding:2px;">상세명 : ${position.wlk_cours_nm}</div>` +
          //  `<div style="padding:2px;">난이도 : ${position.cours_level_nm}</div>` +
            //  `<div style="padding:2px;">코스 길이 : ${position.cours_lt_cn}</div>` +
            //  `<div style="padding:2px;">상세 : ${position.cours_detail_lt_cn}</div>` +
            //  `<div style="padding:2px;">예상 소요 시간 : ${position.cours_time_cn}</div>` +
            `<div style="padding:2px;">위치 : ${position.sigungu}</div></div>`
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
            position: position.latlng,
          });

        if(position.beach_img != null){
          const imgSize = new kakao.maps.Size(24, 35);
          const markerImage = new kakao.maps.MarkerImage(position.beach_img, imgSize);
          marker.image = markerImage;
        }

        // 이벤트 등록
        //  kakao.maps.event.addListener(marker, "mouseover", () => {infowindow.open(this.map, marker);});
        kakao.maps.event.addListener(marker, "click", () => {
          this.surfingPoint = position.latlng;
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
  },
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