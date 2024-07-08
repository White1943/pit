<template>
    <div style="width: 100vw; min-height: 100vh;">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
               @select="handleSelect" style="flex-direction: row-reverse;" router="true">
        <el-sub-menu index="1">
          <template #title>我的</template>
          <el-menu-item index="/login">退出登录</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="/admin/order">订单管理</el-menu-item>
        <el-menu-item index="/admin/pet">宠物管理</el-menu-item>
        <el-menu-item index="/admin/user">用户管理</el-menu-item>
      </el-menu>
  
      <el-input v-model="searchKeyword" placeholder="输入用户名搜索" style="width: 300px;"></el-input>
      <el-button type="primary" @click="fetchUsers(1)">搜索</el-button>
      <el-button @click="resetSearch">重置</el-button>
  
      <el-table :data="users" class="user-table-container" style="width: 100%">
        <el-table-column prop="id" label="ID" width="120" />
        <el-table-column prop="username" label="用户名" width="180" />
        <el-table-column prop="email" label="邮箱" width="180" />
        <el-table-column prop="phoneNumber" label="电话号码" width="180" />
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button type="primary" size="mini" @click="editUser(scope.row)">编辑</el-button>
            <el-button type="danger" size="mini" @click="deleteUser(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <el-pagination @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pageSize"
                     layout="total, prev, pager, next" :total="total"></el-pagination>
  
      <el-dialog v-model="dialogVisible" title="编辑用户">
        <el-form :model="userForm">
          <el-form-item label="用户名">
            <el-input v-model="userForm.username"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="userForm.email"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="userForm.phoneNumber"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveUser">保存</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  import { ElNotification, ElMessageBox } from 'element-plus';
  
  export default {
    name: 'UserManagement',
  
    setup() {
      const activeIndex = ref('/admin/user');
      const users = ref([]);
      const currentPage = ref(1);
      const pageSize = ref(10);
      const total = ref(0);
      const searchKeyword = ref('');
      const dialogVisible = ref(false);
      const userForm = ref({
        id: '',
        username: '',
        email: '',
        phoneNumber: '',
      });
  
      const fetchUsers = async (page = 1) => {
        try {
          const response = await axios.post('http://localhost:15080/api/user/userFindList', {
            pageNum: page,
            pageSize: pageSize.value,
            msg:searchKeyword.value
          });
          const data = response.data;
          if (data.code === '200') {
            users.value = data.data.records;
            total.value = data.data.total;
          } else {
            ElNotification({
              title: 'Error',
              message: data.msg,
              type: 'error',
              position: 'top-right',
            });
          }
        } catch (error) {
          console.error('Failed to fetch users:', error);
        }
      };
  
      const handleCurrentChange = (page) => {
        currentPage.value = page;
        fetchUsers(page);
      };
  
      const editUser = (user) => {
        userForm.value = { ...user };
        dialogVisible.value = true;
      };
  
      const saveUser = async () => {
        try {
          const response = await axios.post('http://localhost:15080/api/user/userUpdate', userForm.value);
          if (response.data.code === '200') {
            ElNotification({
              title: 'Success',
              message: response.data.msg,
              type: 'success',
              position: 'top-right',
            });
            dialogVisible.value = false;
            fetchUsers(currentPage.value);
          } else {
            ElNotification({
              title: 'Error',
              message: response.data.msg,
              type: 'error',
              position: 'top-right',
            });
          }
        } catch (error) {
          console.error('Failed to update user:', error);
        }
      };
  
      const deleteUser = async (userId) => {
        try {
          const confirmResult = await ElMessageBox.confirm('确定删除该用户吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          });
          if (confirmResult === 'confirm') {
            const response = await axios.delete(`http://localhost:15080/api/user/userRemove?id=${userId}`);
            if (response.data.code === '200') {
              ElNotification({
                title: 'Success',
                message: response.data.msg,
                type: 'success',
                position: 'top-right',
              });
              fetchUsers(currentPage.value);
            } else {
              ElNotification({
                title: 'Error',
                message: response.data.msg,
                type: 'error',
                position: 'top-right',
              });
            }
          }
        } catch (error) {
          console.error('Failed to delete user:', error);
        }
      };
  
      const resetSearch = () => {
        searchKeyword.value = '';
        fetchUsers(1);
      };
  
      onMounted(() => {
        fetchUsers();
      });
  
      return {
        activeIndex,
        users,
        currentPage,
        pageSize,
        total,
        searchKeyword,
        dialogVisible,
        userForm,
        fetchUsers,
        handleCurrentChange,
        editUser,
        saveUser,
        deleteUser,
        resetSearch,
      };
    },
  };
  </script>
  
  <style scoped>
  .user-table-container {
    max-width: 900px;
    padding: 20px;
    border-width: 3px;
    background-color: #ffffff;
    box-shadow: 0 8px 12px 0 rgba(28, 75, 215, 0.5);
    border-radius: 10px;
    width: 100%;
    margin: 20px auto;
  }
  
  .dialog-footer {
    text-align: right;
  }
  </style>
  