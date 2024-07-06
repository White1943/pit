import { createRouter, createWebHistory } from 'vue-router';
import UserHome from './views/UserHome.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import Feed from './views/Feed.vue';
import UserInfo from './views/UserInfo.vue';
import UserLog from './views/UserLog.vue';
import MyPet from './views/MyPet.vue';
import OrderManage from './views/OrderManage.vue'
import UserManage from './views/UserManage.vue'
import PetManage from './views/PetManage.vue'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/user/home',
    name: 'UserHome',
    component: UserHome,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
  {
    path: '/user/feed',
    name: 'Feed',
    component: Feed,
  },
  {
    path: '/user/info',
    name: "UserInfo",
    component: UserInfo,
  },
  {
    path: '/user/log',
    name: "UserLog",
    component: UserLog,
  },
  {
    path: '/user/pet',
    name: "MyPet",
    component: MyPet,
  },
  {
    path: '/admin/user',
    name: "UserManage",
    component: UserManage,
  },
  {
    path: '/admin/order',
    name: "OrderManage",
    component: OrderManage,
  },
  {
    path: '/admin/pet',
    name: "PetManage",
    component: PetManage,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
