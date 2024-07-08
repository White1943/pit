<template>
    <div style="width: 100vw; min-height: 100vh;">
 
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
               @select="handleSelect" style="flex-direction: row-reverse;" router="true">
        <el-sub-menu index="1">
          <template #title>我的</template>
          <el-menu-item index="/user/info">个人信息</el-menu-item>
          <el-menu-item index="/login">退出登录</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="/user/pet">我的宠物</el-menu-item>
        <el-menu-item index="/user/feed">留言记录</el-menu-item>
        <el-menu-item index="/user/log">遛宠记录</el-menu-item>
        <el-menu-item index="/user/home">首页</el-menu-item>
      </el-menu>
  
      <el-card class="info-card">
        <div slot="header">
          <span>个人信息</span>
        </div>
        <el-form :model="userInfo" :rules="rules" ref="infoForm" label-width="100px" class="info-form">
          <el-form-item label="用户ID">
            <el-input v-model="userInfo.id" disabled></el-input>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input v-model="userInfo.username"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="userInfo.password"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="userInfo.email"></el-input>
          </el-form-item>
          <el-form-item label="电话号码" prop="phoneNumber">
            <el-input v-model="userInfo.phoneNumber"></el-input>
          </el-form-item>
          <el-form-item label="其他信息" prop="otherInfo">
            <el-input type="textarea" v-model="userInfo.otherInfo"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updateInfo">保存修改</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script setup>
  import { ref, reactive } from 'vue';
  import axios from 'axios';
  import { ElNotification } from 'element-plus';
  import { useRouter } from 'vue-router'; 
  
  const activeIndex = ref('/user/info');
  const router = useRouter(); // Initialize the router instance
  
  const userInfo = reactive({
    id: '',
    username: '',
    password: '',
    email: '',
    phoneNumber: '',
    otherInfo: ''
  });
  
  const rules = {
    email: [
      { required: true, message: '请输入邮箱', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
    ],
    phoneNumber: [
      { required: true, message: '请输入电话号码', trigger: 'blur' }
    ]
  };
  
  const handleSelect = (index) => {
    // 处理菜单选项选择逻辑，如果需要的话
  };
  
  const updateInfo = async () => {
    try {
      const response = await axios.post('http://localhost:15080/api/user/userUpdate', userInfo);
      if (response.data.code === '200') {
        console.log('信息更新成功');
        ElNotification({
          title: 'Success',
          message: '信息更新成功',
          type: 'success',
          position: 'top-right',
        });
        router.push('/login'); // Use router.push to navigate
      } else {
        console.error('信息更新失败:', response.data.msg);
        ElNotification({
          title: 'Error',
          message: response.data.msg,
          type: 'error',
          position: 'top-right',
        });
      }
    } catch (error) {
      console.error('信息更新失败:', error);
      ElNotification({
        title: 'Error',
        message: '信息更新失败',
        type: 'error',
        position: 'top-right',
      });
    }
  };
  
  const resetForm = () => {
    // 重新加载用户信息以确保与sessionStorage中的数据同步
    const userData = JSON.parse(sessionStorage.getItem('user'));
    if (userData) {
      userInfo.id = userData.id;
      userInfo.username = userData.username;
      userInfo.password = userData.password;
      userInfo.email = userData.email;
      userInfo.phoneNumber = userData.phoneNumber;
      userInfo.otherInfo = userData.otherInfo;
    } else {
      userInfo.id = '';
      userInfo.username = '';
      userInfo.password = '';
      userInfo.email = '';
      userInfo.phoneNumber = '';
      userInfo.otherInfo = '';
    }
  };
  
  // 在页面加载时从sessionStorage中获取用户信息并填充表单
  const userData = JSON.parse(sessionStorage.getItem('user'));
  if (userData) {
    userInfo.id = userData.id;
    userInfo.username = userData.username;
    userInfo.password = userData.password;
    userInfo.email = userData.email;
    userInfo.phoneNumber = userData.phoneNumber;
    userInfo.otherInfo = userData.otherInfo;
  }
  </script>
  
  <style scoped>
  .info-card {
    max-width: 800px;
    margin: 20px auto;
    padding: 20px;
    background-color: #ffffff;
    box-shadow: 0 8px 12px 0 rgba(28, 75, 215, 0.5);
    border-radius: 10px;
  }
  
  .info-form {
    width: 100%;
  }
  </style>
   
