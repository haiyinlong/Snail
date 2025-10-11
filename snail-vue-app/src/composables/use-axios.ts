import type { AxiosError } from 'axios'

import axios from 'axios'

import env from '@/utils/env'

export function useAxios() {
  const axiosInstance = axios.create({
    baseURL: env.VITE_SERVER_API_PREFIX,
    timeout: env.VITE_SERVER_API_TIMEOUT,
  })

 axiosInstance.interceptors.request.use(
    (config) => {
      const token = localStorage.getItem('auth_token')
      if (token) {
        config.headers.Authorization = `Bearer ${token}`
      }
      return config
    },
    (error) => {
      return Promise.reject(error)
    }
  )

  axiosInstance.interceptors.response.use(
    (response) => {
      if (response.data?.code == 401) {
        localStorage.removeItem('auth_token')
        localStorage.removeItem('user_info')
        window.location.href = '/errors/401'
      }
      if (response.data?.code == 200) {
        return response.data
      }
      return response
    },
    (error: AxiosError) => {
      if (error.response?.status === 401) {
        localStorage.removeItem('auth_token')
        localStorage.removeItem('user_info')
        window.location.href = '/errors/401'
      }
      return Promise.reject(error)
    }
  )

  return {
    axiosInstance,
  }
}
