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
</template>
<script setup>
import { ref } from 'vue';
const activeIndex = ref('/admin/pet')
</script>