import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Home.vue'
import Login from '../components/user/LoginPage.vue'
import Join from '../components/user/JoinPage.vue'
import VideoView from '../views/Video.vue'
import VideoList from '../components/video/VideoList.vue'
import VideoDetail from '../components/video/VideoDetail.vue'
import CommunityView from '../views/Community.vue'
import MyPage from '../views/MyPage.vue'
import UpdateUser from '../components/user/UpdateUser.vue'
import UpdatePass from '../components/user/UpdatePass.vue'
import UserList from '../components/community/UserList.vue'
import FollowingList from '../components/community/FollowingList.vue'
import FollowerList from '../components/community/FollowerList.vue'
import FollowProfile from '../components/community/FollowProfile.vue'
import FollowLikeVideo from '../components/community/FollowLikeVideo.vue'
import CommunityBoard from '../components/community/CommunityBoard.vue'
import BoardList from "../components/community/board/BoardList.vue"
import BoardDetail from "../components/community/board/BoardDetail.vue"
import BoardUpdate from "../components/community/board/BoardUpdate.vue"
import TrekkingView from "../views/TrekkingView.vue"
import TrekkingHome from "../components/trekking/TrekkingHome.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/updateUser',
    name: 'UpdateUser',
    component: UpdateUser
  },
  {
    path: '/trekking',
    component: TrekkingView,
    children:[
      {
        path: '',
        name : 'TrekkingHome',
        component: TrekkingHome,
      }
    ]
  },
  {
    path: '/updatePass',
    name: 'UpdatePass',
    component: UpdatePass
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/video',
    component: VideoView,
    children:[
      {
        path:'',
        name:'VideoList',
        component: VideoList
      },
      {
        path:'detail',
        name:'VideoDetail',
        component: VideoDetail
      }
    ]
  },
  {
    path: '/community',
    component: CommunityView,
    children:[
      {
        path: '',
        name: 'UserList',
        component: UserList,
      },
      {
        path: 'following',
        name: 'FollowingList',
        component: FollowingList,
      },
      {
        path: 'follower',
        name: 'FollowerList',
        component: FollowerList,
      },
      {
        path: 'followProfile',
        name: 'FollowProfile',
        component: FollowProfile,
      },
      {
        path: 'board',
        component: CommunityBoard,
        children:[
          {
            path: "",
            name: "boardList",
            component: BoardList
          },
          {
            path: ":id",
            name: "boardDetail",
            component: BoardDetail
          },
          {
            path: "update",
            name: "boardUpdate",
            component: BoardUpdate
          }
        ]
      }
    ]
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage
  },
  {
    path: '/likeVideoList',
    name: 'FollowLikeVideo',
    component: FollowLikeVideo
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router