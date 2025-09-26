<script setup lang="ts">
import AppSidebar from '@/components/AppSidebar.vue'
import { SidebarProvider, SidebarTrigger, SidebarInset } from '@/components/ui/sidebar'
import DarkMode from '@/components/DarkMode.vue'
</script>

<template>
  <SidebarProvider>
    <AppSidebar />
    <SidebarInset>
      <header class="flex sticky top-0 bg-background h-12 shrink-0 items-center gap-2 border-b px-4">
        <SidebarTrigger class="-ml-1" />
        <div class="h-4 border-l border-border "></div>
        <DarkMode class="-ml-1" />
      </header>
      <main class="flex-1 p-4">
        <RouterView />
      </main>
    </SidebarInset>
  </SidebarProvider>
</template>