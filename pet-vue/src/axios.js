// src/axios.js
import axios from 'axios';

// 创建一个 axios 实例
const instance = axios.create({
  baseURL: 'http://localhost:15080/api', // 您的后端 API 基础 URL
  timeout: 10000, // 请求超时时间
  headers: { 'Content-Type': 'application/json' }
});

// 请求拦截器
instance.interceptors.request.use(
  config => {
    // 可以在此处添加请求头或者其他配置
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// 响应拦截器
instance.interceptors.response.use(
  response => response,
  error => {
    // 处理响应错误
    return Promise.reject(error);
  }
);

export default instance;
