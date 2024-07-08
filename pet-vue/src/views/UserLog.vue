<template>
    <div style="width: 100vw; min-height: 100vh;">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
               @select="handleSelect" style="flex-direction: row-reverse;" :router="true">
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
  
      <el-table class="product-table-container" :data="orders" style="width: 100%">
        <el-table-column prop="id" label="ID" width="180" fixed />
        <el-table-column prop="petName" label="宠物名称" width="180" />
        <el-table-column label="图片" width="180">
          <template #default="scope">
            <img v-if="scope.row.img" :src="'data:image/jpeg;base64,' + scope.row.img" alt="Pet Image" width="100" />
          </template>
        </el-table-column>
        <el-table-column prop="userName" label="申请人名称" width="180" />
        <el-table-column prop="ownerName" label="宠物主人名称" width="180" />
        <el-table-column prop="time" label="时间" width="180" />
        <el-table-column prop="reviewStatus" label="遛狗状态" width="180"  >
        <template #default="{ row }">
            <el-tag :type="row.reviewStatus === 0 ? 'info' : 'success'">
              {{ row.status === 0 ? '待处理' : '待溜中' }}
            </el-tag>
          </template> </el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button
              v-if="scope.row.reviewStatus !== '1'"
              type="primary"
              size="small"
              @click="approveAdoption(scope.row.id, 1)"
            >通过</el-button>
            <el-button
              v-if="scope.row.reviewStatus !== '2'"
              type="danger"
              size="small"
              @click="approveAdoption(scope.row.id, 2)"
            >拒绝</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next"
        :total="total"
      ></el-pagination>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const activeIndex = ref('/user/log');
  const orders = ref([]);
  const currentPage = ref(1);
  const pageSize = ref(20);
  const total = ref(0);
  
  const fetchOrders = async (page = 1) => {
    try {
      // 获取存储在 sessionStorage 中的用户信息
      const userData = JSON.parse(sessionStorage.getItem('user'));
      const userId = userData.id;
  
      const response = await axios.post('http://localhost:15080/api/adoption/adoptionFindListPlus', {
        pageNum: page,
        pageSize: pageSize.value,
        id: userId
      });
  
      const data = response.data.data;
      console.log(data)
 

      orders.value = data.records.filter(order => order.reviewStatus == 1); // 只保留 viewState === 1 的订单
      console.log(orders)
      total.value = data.total;
    } catch (error) {
      console.error('Failed to fetch orders:', error);
    }
  };
  
  const handleCurrentChange = (page) => {
    currentPage.value = page;
    fetchOrders(page);
  };
  
  const approveAdoption = async (id, status) => {
    try {
      const response = await axios.post('http://localhost:15080/api/adoption/adoptionApproval', {
        id: id,
        reviewStatus: status
      });
      if (response.data.code === '200') {
        fetchOrders(currentPage.value);
      } else {
        console.error('Failed to update adoption status:', response.data.msg);
      }
    } catch (error) {
      console.error('Failed to update adoption status:', error);
    }
  };
  
 
  
  onMounted(() => {
    fetchOrders();
  });
  </script>
  
  <style scoped>
  .product-table-container {
    max-width: 1300px;
    padding: 20px;
    border-width: 3px;
    background-color: #ffffff;
    box-shadow: 0 8px 12px 0 rgba(28, 75, 215, 0.5);
    border-radius: 10px;
    width: 100%;
    margin: 20px auto;
  }
  </style>
  