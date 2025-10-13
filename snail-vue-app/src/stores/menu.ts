// src/stores/menu.ts
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useUserMenuQuery } from '@/services/api/menus.api'
import type { SidebarData, Team } from '@/components/app-sidebar/types'
import type { MenuBarData, NavMainData } from '@/types/nav-menu'
import { useIconStore } from '@/stores/icon'
export const useMenuStore = defineStore('menu', () => {
  // 状态
  const loading = ref(false)
  const sidebarData = ref<SidebarData>()

  const iconStore = useIconStore()

  const transformNavMain = (navItems: any[]) => {
    return navItems.map(item => {
      const transformedItem = { ...item }
      
      if (iconStore.getIcon(item.icon)) {
        transformedItem.icon = iconStore.getIcon(item.icon)
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
      const userMenuQueryResponse = useUserMenuQuery()
      const response = await userMenuQueryResponse.refetch()
      const transformedTeams: Team[] = response.data?.teams
        ? response.data.teams.map(team => {
            const logoIcon = iconStore.getIcon(team.logo)
            return {
              ...team,
              logo: typeof logoIcon === 'string' 
                ? iconStore.getDefaultIcon() 
                : logoIcon
            }
          })
        : []

      const transformedNavMain = response.data?.navMain ? transformNavMain(response.data.navMain) : []
      const menuBarData = transformMenuBarData(response.data?.navMain ?? [])
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
      return response.data
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