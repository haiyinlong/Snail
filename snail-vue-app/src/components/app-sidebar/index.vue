<script lang="ts" setup>
import NavTeam from './nav-team.vue'
import TeamSwitcher from './team-switcher.vue'
import { useMenuStore } from '@/stores/menu'
import { onMounted } from 'vue'

const menuStore = useMenuStore()

onMounted(async () => {
  try {
    await menuStore.getUserMenus()
  } catch (error) {
    console.error('Failed to load sidebar data:', error)
  }
})
</script>


<template>
  <UiSidebar collapsible="icon" style="height: calc(100vh - 1rem);" class="z-50 m-2 rounded-xl border py-3 shadow-sm">
    <UiSidebarHeader>
      <!-- Add v-if to check if sidebarData exists -->
      <TeamSwitcher v-if="menuStore.sidebarData" :teams="menuStore.sidebarData.teams" />
    </UiSidebarHeader>

    <UiSidebarContent>
      <!-- Add v-if to check if sidebarData exists -->
      <NavTeam v-if="menuStore.sidebarData" :nav-main="menuStore.sidebarData.navMain" />
    </UiSidebarContent>
  </UiSidebar>
</template>