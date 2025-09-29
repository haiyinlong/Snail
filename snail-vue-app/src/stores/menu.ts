// src/stores/menu.ts
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useMenusMutation } from '@/services/api/menus.api'
import type { SidebarData, Team } from '@/components/app-sidebar/types'
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

  // Transform navMain data recursively
  const transformNavMain = (navItems: any[]) => {
    return navItems.map(item => {
      const transformedItem = { ...item }
      
      // Convert icon string to component if exists
      if (item.icon && iconMap[item.icon as keyof typeof iconMap]) {
        transformedItem.icon = iconMap[item.icon as keyof typeof iconMap]
      }
      
      // Recursively transform nested items
      if (item.items) {
        transformedItem.items = transformNavMain(item.items)
      }
      
      return transformedItem
    })
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
      
      sidebarData.value = {
        user: {
          name: 'shadcn',
          email: 'm@example.com',
          avatar: '/avatars/shadcn.jpg',
        },
        teams: transformedTeams,
        navMain: transformedNavMain
      }
      console.log('sidebarData:', sidebarData.value)
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