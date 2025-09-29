<script setup lang="ts">
import { ref } from 'vue'
import { useColorMode } from '@vueuse/core'
import { Moon, Sun, SunMoon } from 'lucide-vue-next'
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover'
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from '@/components/ui/tooltip'
import { Button } from '@/components/ui/button'

const mode = useColorMode()
const open = ref(false)

const selectTheme = (newMode: 'light' | 'dark' | 'auto') => {
  mode.value = newMode
  open.value = false
}
const changeTheme = () => {
  selectTheme(mode.value === 'light' ? 'dark' : mode.value === 'dark' ? 'auto' : 'light')
}
</script>

<template>
  <TooltipProvider>
    <Tooltip>
      <TooltipTrigger>
        <Popover v-model:open="open">
          <PopoverTrigger as-child>
            <Button variant="ghost" size="icon" @click="changeTheme()" class="relative rounded-full">
              <Moon class=" rotate-0 scale-100 transition-all dark:-rotate-90 dark:scale-0" />
              <Sun class="absolute  rotate-90 scale-0 transition-all dark:rotate-0 dark:scale-100" />
              <span class="sr-only">{{ $t('common.toggleTheme') }}</span>
            </Button>
          </PopoverTrigger>
        </Popover>
      </TooltipTrigger>
      <TooltipContent side="bottom">
        <p>{{ $t('common.toggleTheme') }}</p>
      </TooltipContent>
    </Tooltip>
  </TooltipProvider>
</template>
