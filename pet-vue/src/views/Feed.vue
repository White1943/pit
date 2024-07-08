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
    <div class="product-table-container" >
      <el-table :data="feedbackList" style="width: 100%" v-loading="loading">
        <el-table-column prop="id" label="ID" width="180" />
        <el-table-column prop="userName" label="用户名" width="180" />
        <el-table-column prop="petName" label="宠物名称" width="180" />
        <el-table-column prop="time" label="时间" width="180" />
        <el-table-column prop="content" label="内容" />
        <!-- <el-table-column prop="status" label="状态" width="120">
          <template #default="{ row }">
            <el-tag :type="row.status === 0 ? 'info' : 'success'">
              {{ row.status === 0 ? '待处理' : '已处理' }}
            </el-tag>
          </template>
        </el-table-column> -->
        <el-table-column label="操作" width="120">
            <template #default="scope">
              <el-button type="danger" size="mini" @click="deleteFeedback(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
      </el-table>
      <el-form ref="feedbackForm" :model="newFeedback" label-width="100px" style="margin-top: 20px;">
        <el-form-item label="宠物ID">
          <el-input v-model="newFeedback.petId" placeholder="请输入宠物ID"></el-input>
        </el-form-item>
        <el-form-item label="健康状态">
          <el-input v-model="newFeedback.health" placeholder="请输入健康状态"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="newFeedback.content" placeholder="请输入留言内容"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addFeedback">提交留言</el-button>
        </el-form-item>
      </el-form>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="pageInfo.pageNum"
        :page-size="pageInfo.pageSize"
        :total="pageInfo.total"
        layout="total, prev, pager, next"
      ></el-pagination>
    </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
    import { ElNotification } from 'element-plus'
  const activeIndex = ref('/user/feed');
  const feedbackList = ref([]);
  const loading = ref(false);
  const pageInfo = ref({
    pageNum: 1,
    pageSize: 10,
    total: 0
  });

  const newFeedback = ref({
  content: '',
  userId: JSON.parse(sessionStorage.getItem('user')).id, 
  petId: '',
  health: '',
});

const addFeedback = async () => {
  try {
    const response = await axios.post('http://localhost:15080/api/feedback/feedbackInsert', newFeedback.value);
    if (response.data.code === '200') {
         fetchFeedbackList();      newFeedback.value.content = '';
      newFeedback.value.petId = '';
      newFeedback.value.health = '';
    } else {
      console.error('Failed to add feedback:', response.data.msg);
    }
  } catch (error) {
    console.error('Failed to add feedback:', error);
  }
};
  const fetchFeedbackList = async () => {
    try {
      loading.value = true;
      const response = await axios.post('http://localhost:15080/api/feedback/feedbackAllList', {
        pageNum: pageInfo.value.pageNum,
        pageSize: pageInfo.value.pageSize
      });
      feedbackList.value = response.data.data.records;
      pageInfo.value.total = response.data.data.total;
    } catch (error) {
      console.error('Failed to fetch feedback list:', error);
    } finally {
      loading.value = false;
    }
  };
   
  const deleteFeedback = async (id) => {
  try {
    const response = await axios.delete(`http://localhost:15080/api/feedback/feedbackDelete?id=${id}`);
    if (response.data.code === '200') {
      // Refresh feedback list after deletion
      fetchFeedbackList();
      ElNotification({
            title: 'Success',
            message: response.data.msg,
            type: 'success',
            position: 'top-right',
          });
    } else {
      console.error('Failed to delete feedback:', response.data.msg);
    }
  } catch (error) {
    console.error('Failed to delete feedback:', error);
  }
};


  const handleCurrentChange = (page) => {
    pageInfo.value.pageNum = page;
    fetchFeedbackList();
  };
  
  onMounted(() => {
    fetchFeedbackList();
  });
  </script>
  

  <style scoped>
  .product-table-container {
    max-width: 1100px;
    padding: 20px;
    border-width: 3px;
    background-color: #ffffff;
    box-shadow: 0 8px 12px 0 rgba(11, 44, 142, 0.5);
    border-radius: 10px;
    width: 100%;
    margin: 20px auto;
  }
  </style>
  
   