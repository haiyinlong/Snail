import path from 'node:path'
import tailwindcss from '@tailwindcss/vite'
import vue from '@vitejs/plugin-vue'
import Icons from 'unplugin-icons/vite'
import { defineConfig } from 'vite'

export default defineConfig({
  plugins: [vue(), tailwindcss(),
    Icons({
      compiler: 'vue3',
      autoInstall: true,
    })],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
})