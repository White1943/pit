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
      <div class="product-table-container">
        <el-table :data="orders" style="width: 100%">
          <el-table-column prop="id" v-if="false" label="ID" width="180" />
          <el-table-column prop="petName" label="宠物名称" width="180" />
          <el-table-column label="图片" width="180">
            <template #default="scope">
              <img v-if="scope.row.img" :src="'data:image/jpeg;base64,' + scope.row.img" alt="Pet Image" width="100" />
            </template>
          </el-table-column>
          <el-table-column prop="userName" label="申请人名称" width="180" />
          <el-table-column prop="ownerName" label="宠物主人名称" width="180" />
          <el-table-column prop="time" label="时间" width="180" />
          <el-table-column prop="reviewStatus" label="审核状态" width="180">
            <template #default="{ row }">
              <el-tag :type="getTagType(row.reviewStatus)">
                {{ getReviewStatusText(row.reviewStatus) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="300">
            <template #default="scope">
              <el-button
                type="primary"
                size="small"
                @click="approveAdoption(scope.row.id, scope.row.petId, 1)"
                :disabled="scope.row.reviewStatus === '1'"
              >通过</el-button>
              <el-button
                type="danger"
                size="small"
                @click="approveAdoption(scope.row.id, scope.row.petId, 2)"
                :disabled="scope.row.reviewStatus === '2'"
              >拒绝</el-button>
              <el-button
                type="danger"
                size="small"
                @click="deleteAdoption(scope.row.id, scope.row.petId)"
              >删除</el-button>
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
      <div class="div2"></div>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue'
  import axios from 'axios'
  
  export default {
    name: 'OrderManagement',
    components: {},
    setup() {
      const activeIndex = ref('/admin/order')
      const orders = ref([])
      const currentPage = ref(1)
      const pageSize = ref(10)
      const total = ref(0)
  
      const fetchOrders = async (page = 1) => {
        try {
          const response = await axios.post('http://localhost:15080/api/adoption/adoptionFindListPlus', {
            pageNum: page,
            pageSize: pageSize.value
          })
          const data = response.data.data
          orders.value = data.records
          total.value = data.total
        } catch (error) {
          console.error('Failed to fetch orders:', error)
        }
      }
  
      const handleCurrentChange = (page) => {
        currentPage.value = page
        fetchOrders(page)
      }
  
      const approveAdoption = async (id, petId, status) => {
        try {
          const response = await axios.post('http://localhost:15080/api/adoption/adoptionApproval', {
            id: id,
            petId: petId,
            reviewStatus: status
          })
          if (response.data.code === '200') {
            fetchOrders(currentPage.value)
          } else {
            console.error('Failed to update adoption status:', response.data.msg)
          }
        } catch (error) {
          console.error('Failed to update adoption status:', error)
        }
      }
  
      const deleteAdoption = async (id, petId) => {
        try {
          const response = await axios.post('http://localhost:15080/api/adoption/adoptionDelete', {
            id: id,
            petId: petId
          })
          if (response.data.code === '200') {
            fetchOrders(currentPage.value)
          } else {
            console.error('Failed to delete adoption:', response.data.msg)
          }
        } catch (error) {
          console.error('Failed to delete adoption:', error)
        }
      }
  
      const getReviewStatusText = (status) => {
        switch (status) {
          case '0':
            return '待处理'
          case '1':
            return '通过'
          case '2':
            return '拒绝'
          default:
            return '未知'
        }
      }
  
      const getTagType = (status) => {
        switch (status) {
          case '0':
            return 'info'
          case '1':
            return 'success'
          case '2':
            return 'danger'
          default:
            return 'default'
        }
      }
  
      onMounted(() => {
        fetchOrders()
      })
  
      return {
        activeIndex,
        orders,
        currentPage,
        pageSize,
        total,
        handleCurrentChange,
        approveAdoption,
        deleteAdoption,
        getReviewStatusText,
        getTagType,
      }
    }
  }
  </script>
  
  <style scoped>
  .product-table-container {
    max-width: 1400px;
    padding: 20px;
    border-width: 3px;
    background-color: #ffffff;
    box-shadow: 0 8px 12px 0 rgba(11, 44, 142, 0.5);
    border-radius: 10px;
    width: 100%;
    margin: 20px auto;
  }
  </style>
   