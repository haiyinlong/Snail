import type { AxiosError } from 'axios'

import { useMutation, useQuery } from '@tanstack/vue-query'

import { useAxios } from '@/composables/use-axios'

interface getTokenRequest {
  clientId: string
  redirectUri: string
  code: string
  state: string
}
interface getTokenResponse {
  access_token: string
  refresh_token: string
  expires_in: number
  scope: string
  token_type: string
  id_token: string
}

export function useTokenMutation() {
  const { axiosInstance } = useAxios()
  return useMutation<getTokenResponse, AxiosError, getTokenRequest>({
    mutationKey: ['token'],
    mutationFn: async (data: getTokenRequest) => {
      const response = await axiosInstance.get<getTokenResponse>(`/resource/client/token?code=${data.code}&state=${data.state}&redirectUri=${data.redirectUri}&clientId=${data.clientId}`)
      return response.data
    },
  })
}
