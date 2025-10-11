import { defineStore } from 'pinia'
import { readonly, ref } from 'vue'

import { useTokenMutation } from '@/services/api/oidc-token.api'
import { useUserInfoMutation } from '@/services/api/oidc-userinfo.api'

import env from '@/utils/env'

interface User {
  id: string
  name: string
  email: string
  avatar?: string
  permissions?: string[]
}

export const useAuthStore = defineStore('user', () => {
  // 状态
  const isLogin = ref(false)
  const user = ref<User | null>(null)
  const loading = ref(false)

  const token = ref(localStorage.getItem('auth_token') || '')
  if (token.value) {
    isLogin.value = true
    const savedUser = localStorage.getItem('user_info')
    if (savedUser) {
      user.value = JSON.parse(savedUser)
    }
  }

  // 模拟登录
  const login = async (email: string, password: string) => {
    loading.value = true

    try {
      // 模拟 API 调用延迟
      await new Promise(resolve => setTimeout(resolve, 1000))

      // 模拟登录验证（简单示例）
      if (email && password) {
        const mockUser: User = {
          id: '1',
          name: 'Demo User',
          email,
          avatar: 'placeholder.png',
        }
        const mockToken = `mock_jwt_token_${Date.now()}`
        // 保存状态
        isLogin.value = true
        user.value = mockUser
        token.value = mockToken
        // 持久化到 localStorage
        localStorage.setItem('auth_token', mockToken)
        localStorage.setItem('user_info', JSON.stringify(mockUser))

        return { success: true, message: '登录成功' }
      }
      else {
        throw new Error('请输入邮箱和密码')
      }
    }
    catch (error) {
      return {
        success: false,
        message: error instanceof Error ? error.message : '登录失败',
      }
    }
    finally {
      loading.value = false
    }
  }

  // oidc登录,跳转到授权地址
  const oidcLogin = async () => {
    const state = Date.now().toString()
    localStorage.setItem('oidc_state', state)
    const oidcUrl = `${env.VITE_OAUTH_API_URL}${env.VITE_OAUTH_API_PREFIX}?client_id=${env.VITE_OAUTH_CLIENT_ID}&response_type=code&scope=openid%20profile&redirect_uri=${env.VITE_OAUTH_REDIRECT_URI}&state=${state}`
    window.location.href = oidcUrl
  }

  // oidc获取token
  const oidcToken = async (code: string) => {
    loading.value = true
    const state = localStorage.getItem('oidc_state')
    const tokenMutation = useTokenMutation()
    try {
      const tokenResponseData = await tokenMutation.mutateAsync({
        clientId: env.VITE_OAUTH_CLIENT_ID,
        redirectUri: window.location.origin + window.location.pathname,
        code,
        state: state || '',
      })
      token.value = tokenResponseData.access_token
      localStorage.setItem('auth_token', tokenResponseData.access_token)
      localStorage.setItem('id_token', tokenResponseData.id_token)
      return tokenResponseData.access_token
    } catch (error) {
      console.error('获取token失败:', error)
    } finally { 
      loading.value = false
    }
  }

  // oidc获取userInfo
  const getUserInfo = async () => {
    loading.value = true;
    const userInfoMutation = useUserInfoMutation();
    return new Promise<void>((resolve, reject) => {
      userInfoMutation.mutate(undefined, {
        onSuccess: (data) => {
          loading.value = false;
          const userData: User = {
            id: data.userId,
            name: data.realName,
            email: data.email, 
            avatar: 'placeholder.png',
            permissions: ['user.add','user.create'],
          };
          user.value = userData;
          localStorage.setItem('user_info', JSON.stringify(userData));
          resolve();
        },
        onError: (error) => {
          loading.value = false;
          console.error('获取用户信息失败:', error);
          reject(error);
        }
      });
    });
  };

  // 登出
  const logout = () => {
    isLogin.value = false
    user.value = null
    token.value = ''
    // 清除 localStorage
    localStorage.removeItem('auth_token')
    localStorage.removeItem('user_info')
    // 强制跳转到登录页（使用 window.location 确保跳转成功）
    if (typeof window !== 'undefined') {
      window.location.href = '/auth/login'
    }
  }

    // oidc登出
  const oidcLogout = () => {
    isLogin.value = false
    user.value = null
    token.value = ''
    // 清除 localStorage
    localStorage.removeItem('auth_token')
    localStorage.removeItem('user_info')
    const id_token_hint = localStorage.getItem('id_token')
    const logoutRedirectUrl =env.VITE_OAUTH_LOGOUT_REDIRECT_URI
    const encodedRedirectUri = encodeURIComponent(logoutRedirectUrl)
    const oidcLogoutUrl = `${env.VITE_OAUTH_API_URL}${env.VITE_OAUTH_LOGOUT_API_PREFIX}?post_logout_redirect_uri=${encodedRedirectUri}&id_token_hint=${id_token_hint}`
    window.location.href = oidcLogoutUrl
  }

  // 检查是否已登录
  const checkAuth = () => {
    return isLogin.value && token.value
  }

  return {
    // 状态
    isLogin: readonly(isLogin),
    user: readonly(user),
    loading: readonly(loading),
    token: readonly(token),
    // 方法
    login,
    oidcLogin,
    oidcToken,
    getUserInfo,
    logout,
    oidcLogout,
    checkAuth,
  }
})
