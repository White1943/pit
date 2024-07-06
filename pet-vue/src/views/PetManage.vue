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

    </div>
    
 
            <el-input v-model="searchKeyword" placeholder="输入宠物名称搜索" style="width: 300px;"></el-input>
            <el-input v-model="searchUserId" placeholder="输入用户ID搜索" style="width: 200px; margin-left: 10px;"></el-input>
            <el-button type="primary" @click="fetchPets(1)">搜索</el-button>
            <el-button @click="resetSearch">重置</el-button>
      

          <!-- Add Pet Button -->
   
            <el-button type="primary" @click="showDialog">添加宠物</el-button>
    

          <!-- Pets Table -->
          <el-table :data="pets" style="width: 100%">
            <el-table-column prop="id" label="ID" width="180" />
            <el-table-column prop="petName" label="宠物名称" width="180" />
            <el-table-column label="图片" width="180">
              <template #default="scope">
                <img v-if="scope.row.img" :src="'data:image/jpeg;base64,' + scope.row.img" alt="Pet Image" width="100" />
              </template>
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="180" />
            <el-table-column prop="color" label="颜色" width="180" />
            <el-table-column prop="sex" label="性别" width="180" />
            <el-table-column prop="breed" label="品种" width="180" />
            <el-table-column prop="isAdopted" label="领养状态" width="180" />
          </el-table>
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total"
          ></el-pagination>

          <!-- Add Pet Dialog -->
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
    
</template>

<script>
import { ref, onMounted } from 'vue'
import axios from 'axios'
 
 
const activeIndex = ref('/admin/pet')
export default {
  name: 'PetManagement',
  components: {
  },
  setup() {
    const sidebarOpen = ref(false)
    const pets = ref([])
    const currentPage = ref(1)
    const pageSize = ref(5)
    const total = ref(0)
    const searchKeyword = ref('')
    const searchUserId = ref('')
    const dialogVisible = ref(false)
    const petForm = ref({
      petName: '',
      age: '',
      color: '',
      sex: '',
      breed: '',
      img: '',
    })
    const fileList = ref([])

    const fetchPets = async (page = 1) => {
      try {
        const response = await axios.post('http://localhost:15080/api/pet/petListAndPage', {
          pageNum: page,
          pageSize: pageSize.value,
          msg: searchKeyword.value,
          id: searchUserId.value
        })
        const data = response.data.data
        pets.value = data.records
        total.value = data.total
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
      // console.log(file)
      alert("图片已添加")
    }

    const addPet = async () => {
      try {
        const formData = new FormData()
        formData.append('file', fileList.value[0])
        const uploadResponse = await axios.post('http://localhost:15080/api/pet/uploadImg', formData)
        petForm.value.img = uploadResponse.data.data.imgName

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

    const resetSearch = () => {
      searchKeyword.value = ''
      searchUserId.value = ''
      fetchPets(1)
    }

    onMounted(() => {
      fetchPets()
    })

    return {
      sidebarOpen,
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
      resetSearch,
    }
  }
}
</script>

<style>
 
</style>

 

 