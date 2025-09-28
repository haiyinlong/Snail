import type { AxiosError } from 'axios'

import { useMutation, useQuery } from '@tanstack/vue-query'

import { useAxios } from '@/composables/use-axios'

interface getUserInfoResponse {
  userId: string
  realName: string
}
export function useUserInfoMutation() {
  const { axiosInstance } = useAxios()
  return useMutation<getUserInfoResponse,AxiosError>({
    mutationKey: ['userInfo'],
    mutationFn: async () => {
      const response = await axiosInstance.get<getUserInfoResponse>(`/auth/userinfo`)
      return response.data
    },
  })
}
