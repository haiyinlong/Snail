import { defineStore } from 'pinia'
import { ref, readonly } from 'vue'


export const useAuthStore = defineStore('user', () => {
  // 状态
  const isLogin = ref(false)
  const loading = ref(false)

  // 从 localStorage 恢复登录状态
  const token = ref(localStorage.getItem('token') || '')
  const oidcLoginUrl = ref(localStorage.getItem('oidcLoginUrl') || 'http://192.168.5.228:9001/auth/oauth2/authorize?client_id=4fef8b86-b712-49bc-8e21-86abfb048a7d&response_type=code&scope=openid profile&redirect_uri=http://localhost:5173/login')  
  // 检查是否已登录
  const checkAuth = () => {
     const token = ref(localStorage.getItem('token') || '')
     if (token.value) {
      isLogin.value = true
      return true;
    }
    return false;
  }

  return {
    // 状态
    isLogin: readonly(isLogin),
    loading: readonly(loading),
    token: readonly(token),
    checkAuth,
    oidcLoginUrl
  }
})