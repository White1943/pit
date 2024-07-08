// src/axios.js
import axios from 'axios';
 
const instance = axios.create({
  baseURL: 'http://localhost:15080',  
  timeout: 10000, // 请求超时时间
  headers: { 'Content-Type': 'application/json' }
});

 
instance.interceptors.request.use(
  config => {
 
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);
 
instance.interceptors.response.use(
  response => response,
  error => {
     return Promise.reject(error);
  }
);

export default instance;
