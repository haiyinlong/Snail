import type { AxiosError } from 'axios'

import { useMutation, useQuery } from '@tanstack/vue-query'

import { useAxios } from '@/composables/use-axios'

interface TeamData {
  name: string;
  logo: string; // 使用字符串类型而不是组件对象
  plan: string;
}

interface NavMainData {
  title: string;
  url?: string;
  icon?: string; // 使用字符串类型而不是组件对象
  items: (NavMainData | { title: string; url: string })[];
}

interface getUserMenuResponse {
  teams: TeamData[]
  navMain: NavMainData[]
}
export function useMenusMutation() {
  const { axiosInstance } = useAxios()
  return useMutation<getUserMenuResponse,AxiosError>({
    mutationKey: ['userMenu'],
    mutationFn: async () => {
      // const response = await axiosInstance.get<getUserMenuResponse>(`/authorization/menus`)
      // return response.data
       return new Promise((resolve) => {
        setTimeout(() => {
          resolve({
            teams: [
              {
                name: 'Acme Inc',
                logo: 'GalleryVerticalEnd',
                plan: 'Enterprise',
              },
              {
                name: 'Acme Corp.',
                logo: 'AudioWaveform',
                plan: 'Startup',
              },
              {
                name: 'Evil Corp.',
                logo: 'Command',
                plan: 'Free',
              },
            ],
            navMain: [
              {
                title: 'common.general',
                items: [
                  {
                    title: 'common.dashboard',
                    url: '/dashboard',
                    icon: 'LayoutDashboard',
                  },
                  {
                    title: 'common.tasks',
                    url: '/tasks',
                    icon: 'ListTodo',
                  },
                  {
                    title: 'common.apps',
                    url: '/apps',
                    icon: 'Boxes',
                  },
                  {
                    title: 'common.users',
                    url: '/users',
                    icon: 'Users',
                  },
                  {
                    title: 'common.aiTalk',
                    url: '/ai-talk',
                    icon: 'Podcast',
                  },
                ],
              },
              {
                title: 'common.pages',
                items: [
                  {
                    title: 'common.auth',
                    icon: 'SquareUserRound',
                    items: [
                      { title: 'common.signIn', url: '/auth/sign-in' },
                      { title: 'common.login', url: '/auth/login' },
                      { title: 'common.signUp', url: '/auth/sign-up' },
                      { title: 'common.forgotPassword', url: '/auth/forgot-password' },
                      { title: 'common.otp', url: '/auth/otp' },
                    ],
                  },
                  {
                    title: 'common.errors',
                    icon: 'Bug',
                    items: [
                      { title: 'common.unauthorized', url: '/errors/401' },
                      { title: 'common.forbidden', url: '/errors/403' },
                      { title: 'common.notFound', url: '/errors/404' },
                      { title: 'common.internalError', url: '/errors/500' },
                      { title: 'common.maintenanceError', url: '/errors/503' },
                    ],
                  },
                  {
                    title: 'common.example',
                    icon: 'FileText',
                    items: [
                      { title: 'common.vditor', url: '/example/vditor' },
                    ],
                  },
                ],
              },
              {
                title: 'common.settings',
                items: [
                  {
                    title: 'common.account',
                    url: '/settings/account',
                    icon: 'User',
                  },
                  {
                    title: 'common.appearance',
                    url: '/settings/appearance',
                    icon: 'Palette',
                  },
                  {
                    title: 'common.notifications',
                    url: '/settings/notifications',
                    icon: 'BellDot',
                  },
                  {
                    title: 'common.billing',
                    url: '/billing',
                    icon: 'CreditCard',
                  },
                ],
              },
              {
                title: 'common.help',
                items: [
                  {
                    title: 'common.helpCenter',
                    url: '/help-center',
                    icon: 'BadgeHelp',
                  },
                ],
              },
            ]
          })
        }, 300) 
        }
    )}
  })
}
