<template>
  <div class="login-page">
    <el-card class="box-card">
      <div slot="header" class="clearfix" style="margin-bottom: 20px;">
        <span class="login-title">🔐大学生遛宠管理系统</span>
      </div>
      <div class="login-form">
        <el-form :model="form" :rules="loginRules" ref="loginForm">
          <el-form-item prop="userName">
            <el-input type="text" v-model="form.userName" auto-complete="off" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="passWord">
            <el-input type="password" v-model="form.passWord" auto-complete="off" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <div style="display: flex;">
              <div>还没有账号？</div>
              <router-link to="/register">注册</router-link>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button style="width:100%;" type="primary" @click="handleLogin" :loading="loading">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      loading: false,
      form: {
        userName: '',
        passWord: ''
      },
      loginRules: {
        userName: [
          { required: true, message: '请输入账户', trigger: 'blur' },
        ],
        passWord: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate().then(async () => {
        this.loading = true;

        try {
          const response = await this.$axios.post('/user/userLogin', {
            username: this.form.userName,
            password: this.form.passWord
          });

          if (response.data.code === '200') {
            const userData = { ...response.data.data };
            delete userData.password; // 删除密码字段

            // 存储用户信息到 sessionStorage
            sessionStorage.setItem('user', JSON.stringify(userData));
            console.log(sessionStorage.getItem('user'))
            this.$router.push('/user/home');
          } else {
            this.$message({
              message: response.data.msg,
              type: 'warning'
            });
          }
        } catch (error) {
          console.error('登录失败:', error);
          this.$message({
            message: '登录失败，请稍后重试',
            type: 'error'
          });
        } finally {
          this.loading = false;
        }
      }).catch(() => {
        this.$message({
          message: '输入错误！',
          type: 'warning'
        });
      });
    }
  }
};
</script>

<style scoped>
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
