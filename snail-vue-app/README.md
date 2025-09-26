# Vue 3 + TypeScript + Vite

This template should help get you started developing with Vue 3 and TypeScript in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

Learn more about the recommended Project Setup and IDE Support in the [Vue Docs TypeScript Guide](https://vuejs.org/guide/typescript/overview.html#project-setup).


创建项目步骤



```bash
npm create vite@latest snail-vue-app -- --template vue-ts
cd .\snail-vue-app\
npm install tailwindcss @tailwindcss/vite
npm install -D @types/node
npx shadcn-vue@latest init
npm install @vueuse/core  
npx shadcn-vue@latest add -a
npm install nprogress
npm i --save-dev @types/nprogress
npm i @formkit/auto-animate
npm i i18n
npm install vue-i18n@next
npm i pinia
npm install pinia-plugin-persistedstate
npm i @tanstack/vue-query
 ```

npm install @radix-icons/vue