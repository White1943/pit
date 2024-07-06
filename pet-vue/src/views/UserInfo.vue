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
    </div>
</template>
<script setup>
import { ref } from 'vue';
const activeIndex = ref('/user/info')
</script>