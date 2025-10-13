// src/services/api/menus.api.ts

import type { AxiosError } from 'axios'
import { useQuery } from '@tanstack/vue-query'
import { useAxios } from '@/composables/use-axios'
import type { TeamData, NavMainData } from '@/types/nav-menu'

// 🔹 数据类型
export interface getUserMenuResponse {
  teams: TeamData[]
  navMain: NavMainData[]
}

// 🔹 1. 纯函数 API（可在任何地方调用）
export async function fetchUserMenuApi(): Promise<getUserMenuResponse> {
  const { axiosInstance } = useAxios()
  const response = await axiosInstance.get<getUserMenuResponse>('/authorization/teamMenu')
  return response.data
}

// 🔹 2. Hook API（用于组件，享受 vue-query 的所有功能）
export function useUserMenuQuery() {
  return useQuery<getUserMenuResponse, AxiosError>({
    queryKey: ['userMenu'],
    queryFn: fetchUserMenuApi, // 复用纯函数
    // 可配置：缓存时间、重试、刷新等
    staleTime: 1000 * 60 * 5, // 5分钟
  })
}
