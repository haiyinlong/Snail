// src/stores/menu.ts
import { defineStore } from 'pinia'
import { useAllMenuMutation ,addMenuApi } from '@/services/api/menu-management.api'
import type { Menu } from '@/pages/system/menu/data/schema'
import { ref } from 'vue'

export const useMenuManagementStore = defineStore('menuManagement', () => {
  const menuData = ref<Menu[]>([])
  const getMenu = async () => {
    try {
      const menusManagementMutation = useAllMenuMutation()
      const response = await menusManagementMutation.mutateAsync()
      menuData.value = response
      return response
    } catch (error) {
      console.error('Failed to fetch menu data:', error)
      throw error
    } finally {
    }
  }

  const addMenu = async (menu: Menu) => {
    try {
      const response = await addMenuApi(menu)
      return response
    } catch (error) {
      console.error('Failed to add menu:', error)
      throw error
    } finally {
    }
  }

  return {
    menuData,
    getMenu,
    addMenu
  }
})