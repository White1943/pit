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
                
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'

// 引入本地图片
const image1 = ref(new URL('../assets/slide.jpg', import.meta.url).href)
const image2 = ref(new URL('../assets/slide.jpg', import.meta.url).href)
const image3 = ref(new URL('../assets/slide.jpg', import.meta.url).href)
console.log(image1.value)
const activeIndex = ref('/user/home')
const handleSelect = (key, keyPath) => {
    console.log(key, keyPath)
}

</script>

<style></style>