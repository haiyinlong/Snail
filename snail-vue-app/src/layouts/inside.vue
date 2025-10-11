<script setup lang="ts">
import { useCookies } from '@vueuse/integrations/useCookies'

import AppSidebar from '@/components/app-sidebar/index.vue'
import CommandMenuPanel from '@/components/command-menu-panel/index.vue'
import ThemePopover from '@/components/custom-theme/theme-popover.vue'
import FullscreenToggle from '@/components/fullscreen-toggle.vue'
import LanguageChange from '@/components/language-change.vue'
import MainNav from '@/components/main-nav.vue'
import TabManager from '@/components/tab-system/TabManager.vue'
import ToggleTheme from '@/components/toggle-theme.vue'
import UserDropdown from '@/components/user-dropdown.vue'
import BreadcrumbBar from '@/components/breadcrumb-bar.vue'

const defaultOpen = useCookies(['sidebar:state'])
</script>

<template>
  <UiSidebarProvider :default-open="defaultOpen.get('sidebar:state')" class="h-screen min-h-0">
    <AppSidebar />
    <UiSidebarInset
      class="w-full max-w-full h-full flex flex-col min-h-0 peer-data-[state=collapsed]:w-[calc(100%-var(--sidebar-width-icon)-1rem)] peer-data-[state=expanded]:w-[calc(100%-var(--sidebar-width))] ml-3">

      <div class="h-full overflow-y-auto px-4 flex-1 min-h-0">
        <router-view />
      </div>
    </UiSidebarInset>
  </UiSidebarProvider>
</template>
