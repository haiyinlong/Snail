import type { AxiosError } from 'axios'

import { useMutation, useQuery } from '@tanstack/vue-query'

import { useAxios } from '@/composables/use-axios'
import type { Menu } from '@/pages/system/menu/data/schema'

export function useAllMenuMutation() {
  const { axiosInstance } = useAxios()
  return useMutation<Menu[],AxiosError>({
    mutationKey: ['allMenu'],
    mutationFn: async () => {
      const response = await axiosInstance.post<Menu[]>(`/authorization/menu/all`)
      return response.data
    }
  })
}

// 导出纯函数
export async function addMenuApi(menu: Menu): Promise<Menu> {
  const { axiosInstance } = useAxios()
  const response = await axiosInstance.post<Menu>(`/authorization/menu`, menu)
  return response.data
}
export function useAddMenuMutation() {
  return useMutation<Menu, AxiosError, any>({
    mutationKey: ['addMenu'],
    mutationFn: addMenuApi
  })
}
