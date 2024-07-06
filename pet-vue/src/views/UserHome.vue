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
      <div style="background-color: rgb(248, 248, 255); width: 100vw; padding: 20px;">
        <div style="width: 100%; background-color: white;">
          <div style="width: 100%; display: flex; flex-direction: row; align-items: center; justify-content: space-around; padding: 20px;">
            <div style="width: 60%;">
              <el-carousel height="300px" style="border-radius: 20px;">
                <el-carousel-item>
                  <img :src="image1" alt="图片1" style="width: 100%; height: 100%; object-fit: cover;" />
                </el-carousel-item>
                <el-carousel-item>
                  <img :src="image2" alt="图片2" style="width: 100%; height: 100%; object-fit: cover;" />
                </el-carousel-item>
                <el-carousel-item>
                  <img :src="image3" alt="图片3" style="width: 100%; height: 100%; object-fit: cover;" />
                </el-carousel-item>
              </el-carousel>
            </div>
            <div style="width: 30%;">
              <el-card>
                <template #header>
                  <div>
                    <el-text type="primary">
                      宠物科普 
                    </el-text>
                  </div>
                </template>
              </el-card>
            </div>
          </div>
          <div style="padding: 20px;">
            <el-input v-model="searchQuery" placeholder="输入宠物名字进行搜索" @keyup.enter="fetchPets" style="margin-bottom: 20px;"></el-input>
            <el-button type="primary" @click="fetchPets">搜索</el-button>
            <el-table :data="petList" style="width: 100%; margin-top: 20px;">
              <el-table-column prop="id" label="ID" width="50"></el-table-column>
              <el-table-column prop="petName" label="宠物名字"></el-table-column>
              <el-table-column prop="breed" label="品种"></el-table-column>
              <el-table-column prop="age" label="年龄"></el-table-column>
              <el-table-column prop="sex" label="性别"></el-table-column>
              <el-table-column prop="type" label="类型"></el-table-column>
              <el-table-column prop="color" label="颜色"></el-table-column>
              <el-table-column prop="healthStatus" label="健康状态"></el-table-column>
              <el-table-column prop="address" label="地址"></el-table-column>
              <el-table-column prop="phone" label="电话"></el-table-column>
              <el-table-column prop="isAdopted" label="是否领养"></el-table-column>
            </el-table>
            <el-pagination
              @current-change="handlePageChange"
              :current-page="pageNum + 1"
              :page-size="pageSize"
              layout="prev, pager, next"
              :total="total">
            </el-pagination>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, reactive } from 'vue'
  import axios from 'axios'
  
  const image1 = ref(new URL('../assets/slide.jpg', import.meta.url).href)
  const image2 = ref(new URL('../assets/slide.jpg', import.meta.url).href)
  const image3 = ref(new URL('../assets/slide.jpg', import.meta.url).href)
  const activeIndex = ref('/user/home')
  const handleSelect = (key, keyPath) => {
    console.log(key, keyPath)
  }
  
  const searchQuery = ref('')
  const petList = ref([])
  const total = ref(0)
  const pageNum = ref(0)
  const pageSize = ref(10)
  
  const fetchPets = async () => {
    try {
      const response = await axios.post('http://localhost:15080/api/pet/petListAndPage', {
        id: '1',
        msg: searchQuery.value,
        pageNum: pageNum.value,
        pageSize: pageSize.value
      })
      const data = response.data.data
      petList.value = data.records
      total.value = data.total
    } catch (error) {
      console.error('Error fetching pets:', error)
    }
  }
  
  const handlePageChange = (newPage) => {
    pageNum.value = newPage - 1
    fetchPets()
  }
  
  </script>
  
  <style></style>
  