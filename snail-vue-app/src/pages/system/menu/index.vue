<script setup lang="ts">
import Page from '@/components/global-layout/basic-page.vue'

import { columns } from './components/columns'
import DataTable from './components/data-table.vue'
import TaskCreate from './components/menu-create.vue'
import { useMenuManagementStore } from '@/stores/menu-management.ts'

defineOptions({
  name: 'MenuManagementPage'
})
const menuManagementStore = useMenuManagementStore()

onMounted(async () => {
  try {
    await menuManagementStore.getMenu()
  } catch (error) {
    console.error('Failed to load menu data:', error)
  }
})
</script>

<template>
  <Page :title="$t('system.menu.info.title')" :description="$t('system.menu.info.description')">
    <template #actions>
      <TaskCreate />
    </template>
    <div class="overflow-x-auto">
      <DataTable :data="menuManagementStore.menuData ?? []" :columns="columns" />
    </div>
  </Page>
</template>
