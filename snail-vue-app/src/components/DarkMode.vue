<script setup lang="ts">
import { computed } from 'vue'
import { Icon } from '@iconify/vue'
import { useColorMode } from '@vueuse/core'
import { Button } from '@/components/ui/button'

const mode = useColorMode()

// 定义主题切换函数
function toggleTheme() {
    if (mode.value === 'light') {
        mode.value = 'dark'
    } else if (mode.value === 'dark') {
        mode.value = 'auto'
    } else {
        mode.value = 'light'
    }
}

// 获取当前主题的显示名称
const currentMode = computed(() => {
    if (mode.value === 'light') return 'Light'
    if (mode.value === 'dark') return 'Dark'
    return 'System'
})
</script>

<template>
    <Button variant="ghost" @click="toggleTheme" class="relative">
        <Icon icon="radix-icons:moon"
            class="h-[1.2rem] w-[1.2rem] rotate-0 scale-100 transition-all dark:-rotate-90 dark:scale-0" />
        <Icon icon="radix-icons:sun"
            class="absolute h-[1.2rem] w-[1.2rem] rotate-90 scale-0 transition-all dark:rotate-0 dark:scale-100" />
        <span class="sr-only">Toggle theme (current: {{ currentMode }})</span>
    </Button>
</template>