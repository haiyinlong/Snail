<!-- 获取code码,获取token -->
<script setup lang="ts">
import { ref } from 'vue'
import { parseQuery } from 'vue-router'

import { useAuthStore } from '@/stores/auth'

const authStore = useAuthStore()

const query = ref(parseQuery(location.search))
const code = query.value.code
if (code && typeof code === 'string') {
  // 获取token
  await authStore.oidcToken(code)
  await authStore.getUserInfo()
}
else {
  // 生成code,并跳转到oidc授权页面
  authStore.oidcLogin()
}
</script>

<template>
  <div v-if="!code" class="w-full h-screen lg:grid lg:min-h-[600px] lg:grid-cols-2 xl:min-h-[800px]">
    oidc登录跳转中......
  </div>
  <div v-if="code" class="w-full h-screen lg:grid lg:min-h-[600px] lg:grid-cols-2 xl:min-h-[800px]">
    页面跳转中......
  </div>
</template>
