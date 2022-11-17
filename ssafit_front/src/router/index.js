import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../components/user/LoginPage.vue'
import Join from '../components/user/JoinPage.vue'
import Video from '../views/Video.vue'
import VideoList from '../components/video/VideoList.vue'
import VideoDetail from '../components/video/VideoDetail.vue'
import Community from '../views/Community.vue'
import MyPage from '../views/MyPage.vue'
import UpdateUser from '../components/user/UpdateUser.vue'
import UpdatePass from '../components/user/UpdatePass.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
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
    name: 'Video',
    component: Video,
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
    name: 'Community',
    component: Community
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router