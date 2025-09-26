<!-- src/pages/login/index.vue -->
<script setup lang="ts">
import { useTokenMutation } from '@/services/auth-token.api'
import { parseQuery } from 'vue-router'
import { ref } from 'vue'
import env from '@/services/env'
// Get URL parameters
const query = ref(parseQuery(location.search))

// Initialize login mutation
const tokenMutation = useTokenMutation()

// Handle login when code is present
const code = query.value.code
if (code && typeof code === 'string') {
    tokenMutation.mutate({
        clientId: env.VITE_OAUTH_CLIENT_ID,
        redirectUri: window.location.origin + window.location.pathname,
        code: code,
        state: query.value.state as string || '',
    }, {
        onSuccess: (data) => {
            localStorage.setItem("token", data.access_token)
            window.location.href = "/"
        },
        onError: (error) => {
            console.error('Login failed:', error)
        }
    })
}
</script>
<template>
</template>