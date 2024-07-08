<template>
 
    <div class="login-page">
    <div class="register-container">
        <el-card class="box-card">
            <div slot="header" class="clearfix" style="margin-bottom: 20px;">
              <span class="login-title">🔐大学生遛宠管理系统</span>
            </div>
      <el-form :model="registerForm" ref="registerForm" :rules="rules" label-width="100px" class="register-form">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="registerForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="registerForm.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input type="password" v-model="registerForm.confirmPassword"></el-input>
        </el-form-item>
        <el-form-item label="电子邮件" prop="email">
          <el-input v-model="registerForm.email"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="phoneNumber">
          <el-input v-model="registerForm.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="其他信息" prop="otherInfo">
          <el-input v-model="registerForm.otherInfo"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleRegister">注册</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    </div>
    </div>
 
  </template>
  
  <script>
  import axios from 'axios';
  import { ElNotification } from 'element-plus';
  
  export default {
    name: 'Register',
    data() {
      const validateConfirmPassword = (rule, value, callback) => {
        if (value !== this.registerForm.password) {
          callback(new Error('两次输入的密码不一致'));
        } else {
          callback();
        }
      };
      return {
        registerForm: {
          username: '',
          password: '',
        confirmPassword: '',
          email: '',
          phoneNumber: '',
          otherInfo: ''
        },
        rules: {
          username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
          password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
          confirmPassword: [{ required: true, validator: validateConfirmPassword, trigger: 'blur' }],
          email: [{ required: true, type: 'email', message: '请输入有效的电子邮件', trigger: 'blur' }],
          phoneNumber: [{ required: true, message: '请输入电话号码', trigger: 'blur' }]
        }
      };
    },
    methods: {
      async handleRegister() {
        this.$refs.registerForm.validate(async (valid) => {
          if (valid) {
            try {
              const response = await axios.post('http://localhost:15080/api/user/userInsert', this.registerForm);
              if (response.data.code === '200') {
                ElNotification({
                  title: 'Success',
                  message: '注册成功',
                  type: 'success',
                  position: 'top-right',
                });
                this.handleReset();
              } else {
                ElNotification({
                  title: 'Error',
                  message: response.data.msg,
                  type: 'error',
                  position: 'top-right',
                });
              }
            } catch (error) {
              console.error('Failed to register:', error);
              ElNotification({
                title: 'Error',
                message: '注册失败，请稍后再试',
                type: 'error',
                position: 'top-right',
              });
            }
          }
        });
      },
      handleReset() {
        this.$refs.registerForm.resetFields();
      }
    }
  };
  </script>
  
  <style scoped>
  .register-container {
    max-width: 500px;
    margin: 0 auto; 
    padding: 20px;
    background-color: #ffffff;
    box-shadow: 0 8px 12px 0 rgba(28, 75, 215, 0.5);
    border-radius: 10px;
  }
  
  .register-form {
    width: 100%;
  }
  .login-page {
    background-image: linear-gradient(180deg, #2af598 0%, #009efd 100%);
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .login-title {
    font-size: 20px;
  }
  
  .box-card {
    width: 400px;
  }
  </style>
 
