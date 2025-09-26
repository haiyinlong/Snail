import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useMenuStore = defineStore('menu', () => {
  const menus = ref<any[]>([])
  const loading = ref<boolean>(false)
  const menuIsEmpty = ref<boolean>(false)
  // 获取用户菜单
  const fetchUserMenus = async () => {
    if (loading.value) return
    menuIsEmpty.value = false
    loading.value = true
    try {
      // 调用 API 获取菜单数据
      const response = await fetch('/api/user/menus')
      menus.value = await response.json()
      menuIsEmpty.value = menus.value.length === 0
    } catch (error) {
      console.error('Failed to fetch menus:', error)
    } finally {
      loading.value = false
    }
  }

  return {
    menus,
    loading,
    menuIsEmpty,
    fetchUserMenus
  }
})