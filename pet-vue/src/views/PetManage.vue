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
    <el-input v-model="searchKeyword" placeholder="输入宠物名称搜索" style="width: 300px;"></el-input>
    <!-- <el-input v-model="searchUserId" placeholder="输入用户ID搜索" style="width: 200px; margin-left: 10px;"></el-input> -->
    <el-button type="primary" @click="fetchPets(1)">搜索</el-button>
    <el-button @click="resetSearch">重置</el-button>
 
    <!-- <el-button type="primary" @click="showDialog">添加宠物</el-button> -->
 
    <el-table :data="pets" class="product-table-container" style="width: 100%">
      <el-table-column prop="id" label="ID" width="180" fixed="left" />
      <el-table-column prop="petName" label="宠物名称" width="180" />
      <el-table-column label="图片" width="180">
        <template #default="scope">
          <img v-if="scope.row.img" :src="'data:image/jpeg;base64,' + scope.row.img" alt="Pet Image" width="100" />
        </template>
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="180" />
      <el-table-column prop="color" label="颜色" width="180" />
      <el-table-column prop="sex" label="性别" width="180" />
      <el-table-column prop="breed" label="类别" width="180" />
      <el-table-column prop="healthStatus" label="健康状态" width="180" />
      <el-table-column prop="address" label="地址" width="180" />
      <el-table-column prop="phone" label="联系电话" width="180" />
      <el-table-column prop="isAdopted" label="领养状态" width="180" />
      <el-table-column prop="userId" label="用户ID" width="180" />
      <el-table-column prop="status" label="状态" width="180" />
      <el-table-column label="操作" width="180" fixed="right">
        <template #default="scope">
          <el-button type="danger" @click="deletePet(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pageSize"
      layout="total, prev, pager, next" :total="total"></el-pagination>

    
      <el-dialog v-model="dialogVisible" title="添加宠物">
          <el-form :model="petForm">
            <el-form-item label="宠物名称">
              <el-input v-model="petForm.petName"></el-input>
            </el-form-item>
            <el-form-item label="年龄">
              <el-input v-model="petForm.age"></el-input>
            </el-form-item>
            <el-form-item label="颜色">
              <el-input v-model="petForm.color"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-select v-model="petForm.sex">
                <el-option label="雄性" value="雄性"></el-option>
                <el-option label="雌性" value="雌性"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="类型">
              <el-select v-model="petForm.breed">
                <el-option label="猫" value="猫"></el-option>
                <el-option label="狗" value="狗"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="地址">
              <el-input v-model="petForm.address"></el-input>
            </el-form-item>
            <el-form-item label="图片">
              <el-upload
                action=""
                list-type="picture-card"
                :on-remove="handleRemove"
                :before-upload="handleBeforeUpload"
                :on-preview="handlePreview"
                :file-list="fileList"
              >
                <i class="el-icon-plus"></i>
              </el-upload>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="addPet">确定</el-button>
          </span>
        </el-dialog>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElNotification } from 'element-plus'
 

export default {

name: 'PetManagement',

components: {
 
},
  setup() {
    const activeIndex = ref('/admin/pet');
    const pets = ref([])
    const currentPage = ref(1)
    const pageSize = ref(5)
    const total = ref(0)
    const searchKeyword = ref('')
    const searchUserId = ref('')

     // 定义 activeIndex
    // const handleSelect = (index) => {
    //   // 定义 handleSelect 方法
    //   console.log('Selected index:', index)
    // };
    const dialogVisible = ref(false)
    const petForm = ref({
      petName: '',
      age: '',
      color: '',
      sex: '',
      breed: '',
      healthStatus: '',
      address: '',
      phone: '',
      isAdopted: '',
      userId: '',
      status: '',
      img: '',
    })
    const fileList = ref([])

    const fetchPets = async (page = 1) => {
      try {
                const userData = JSON.parse(sessionStorage.getItem('user'))
        const userId = userData.id
        const phone = userData.phoneNumber
        const response = await axios.post('http://localhost:15080/api/pet/petListAndPage', {
          pageNum: page,
          pageSize: pageSize.value,
          msg: searchKeyword.value,
        
        })
        const data = response.data.data
        pets.value = data.records
        total.value = data.total
      //   const userData = JSON.parse(sessionStorage.getItem('user'))
      //   const userId = userData.id
      //   const phone = userData.phoneNumber
        // alert(userId)
        // alert(phone)
      } catch (error) {
        console.error('Failed to fetch pets:', error)
      }
    }

    const handleCurrentChange = (page) => {
      currentPage.value = page
      fetchPets(page)
    }

    const showDialog = () => {
      dialogVisible.value = true;
    };

    const handleRemove = (file, fileList) => {
      console.log(file, fileList)
    }

    const handleBeforeUpload = (file) => {
      fileList.value.push(file)
      alert("图片已上传")
      return false
    }

    const handlePreview = (file) => {
      alert("图片已添加")
    }

  const addPet = async () => {
    try {
      const userData = JSON.parse(sessionStorage.getItem('user'))
  const userId = userData.id
  const phone = userData.phoneNumber
      const formData = new FormData()
      formData.append('file', fileList.value[0])
      const uploadResponse = await axios.post('http://localhost:15080/api/pet/uploadImg', formData)
      petForm.value.img = uploadResponse.data.data.imgName
      petForm.value.phone=phone
      petForm.value.userId=userId
      petForm.value.healthStatus='健康'
      petForm.value.status=1
 

      const response = await axios.post('http://localhost:15080/api/pet/petInsert', petForm.value)
      if (response.data.code === '200') {
      
        fileList.value.push({
          name: uploadResponse.data.data.imgName, // 根据实际返回的图片信息修改
          url: 'data:image/jpeg;base64,' + uploadResponse.data.data.img, // 根据实际返回的图片信息修改
        });
   
        dialogVisible.value = false;
        fetchPets(currentPage.value); // 更新宠物列表
      } else {
        console.error('Failed to add pet:', response.data.msg)
      }
    } catch (error) {
      console.error('Failed to add pet:', error)
    }
  }
 

    const deletePet = async (id) => {
      try {
        const response = await axios.delete('http://localhost:15080/api/pet/petRemove', {  data: { id } })
        if (response.data.code === '200') {
          fetchPets(currentPage.value)
          ElNotification({
          title: 'Success',
          message: response.data.msg,
          type: 'success',
          position: 'top-right',
        });
        } else {
          console.error('Failed to delete pet:', response.data.msg)
        }
      } catch (error) {
        console.error('Failed to delete pet:', error)
      }
    }

    const resetSearch = () => {
      searchKeyword.value = ''
      searchUserId.value = ''
      fetchPets(1)
    }

    onMounted(() => {
      fetchPets()
    })

    return {
      activeIndex, 
      pets,
      currentPage,
      pageSize,
      total,
      searchKeyword,
      searchUserId,
      dialogVisible,
      showDialog,
      petForm,
      fileList,
      handleCurrentChange,
      fetchPets,
      handleRemove,
      handleBeforeUpload,
      handlePreview,
      addPet,
      deletePet,
      resetSearch,
    }
  }
}
</script>

<style scoped>
.product-table-container {
  max-width: 2200px;
  padding: 20px;
  border-width: 3px;
  background-color: #ffffff;
  box-shadow: 0 8px 12px 0 rgba(28, 75, 215, 0.5);
  border-radius: 10px;
  width: 100%;
  margin: 20px auto;
}
</style> 