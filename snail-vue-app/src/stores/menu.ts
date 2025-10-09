// src/stores/menu.ts
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useMenusMutation } from '@/services/api/menus.api'
import type { SidebarData, Team } from '@/components/app-sidebar/types'
import type { MenuBarData, NavMainData } from '@/types/nav-menu'
import {
  AudioWaveform,
  Command,
  GalleryVerticalEnd,
  // Add more icons for navMain
  LayoutDashboard,
  ListTodo,
  Boxes,
  Users,
  Podcast,
  SquareUserRound,
  Bug,
  FileText,
  User,
  Palette,
  BellDot,
  CreditCard,
  BadgeHelp
} from 'lucide-vue-next'

export const useMenuStore = defineStore('menu', () => {
  // 状态
  const loading = ref(false)
  const sidebarData = ref<SidebarData>()

  // Icon mapping for converting string identifiers to components
  const iconMap = {
    // Team icons
    'GalleryVerticalEnd': GalleryVerticalEnd,
    'AudioWaveform': AudioWaveform,
    'Command': Command,
    // NavMain icons
    'LayoutDashboard': LayoutDashboard,
    'ListTodo': ListTodo,
    'Boxes': Boxes,
    'Users': Users,
    'Podcast': Podcast,
    'SquareUserRound': SquareUserRound,
    'Bug': Bug,
    'FileText': FileText,
    'User': User,
    'Palette': Palette,
    'BellDot': BellDot,
    'CreditCard': CreditCard,
    'BadgeHelp': BadgeHelp
  }

  const transformNavMain = (navItems: any[]) => {
    return navItems.map(item => {
      const transformedItem = { ...item }
      
      if (item.icon && iconMap[item.icon as keyof typeof iconMap]) {
        transformedItem.icon = iconMap[item.icon as keyof typeof iconMap]
      }
      
      if (item.items) {
        transformedItem.items = transformNavMain(item.items)
      }
      return transformedItem
    })
  }

  const transformMenuBarData = (mavMenuBarData: NavMainData[]) => {
    const menuBarData:MenuBarData[] = []

    mavMenuBarData.map(item => {      
      if (item.url) {
        menuBarData.push({ title: item.title, url: item.url })
      }
      
      const transformedItem: NavMainData = { ...item } as NavMainData

      if (transformedItem.items) {
        const itemMenuBarData = transformMenuBarData(transformedItem.items)
        menuBarData.push(...itemMenuBarData)
      }
    })
    return menuBarData
  }
  const getUserMenus = async () => {
    loading.value = true
    try {
      const menusMutation = useMenusMutation()
      const response = await menusMutation.mutateAsync()

      const transformedTeams = response.teams?.map(team => ({
        ...team,
        logo: iconMap[team.logo as keyof typeof iconMap] || GalleryVerticalEnd
      })) || []
      
      // Transform navMain data with icon conversion
      const transformedNavMain = response.navMain ? transformNavMain(response.navMain) : []
      // 转化 面包屑bar MenuBarData
      const menuBarData = transformMenuBarData(response.navMain)
      localStorage.setItem('menuBarData', JSON.stringify(menuBarData))

      sidebarData.value = {
        user: {
          name: 'shadcn',
          email: 'm@example.com',
          avatar: '/avatars/shadcn.jpg',
        },
        teams: transformedTeams,
        navMain: transformedNavMain
      }
      return response
    } catch (error) {
      console.error('Failed to fetch menu data:', error)
      throw error
    } finally {
      loading.value = false
    }
  }

  return {
    loading,
    sidebarData,
    // 方法
    getUserMenus,
  }
})