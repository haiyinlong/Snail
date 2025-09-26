import type { Router } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

export function authGuard(router: Router) {
    router.beforeEach(async (to, from, next) => {
        // 本地有token 就继续，没有token 就跳转登录
        const authStore = useAuthStore()
        if(authStore.checkAuth()){
            next()
        }else if(to.path === '/login' && to.query.code){
            next()
        }else if (typeof window !== 'undefined') {
            // oidc 登录 url 强制跳转到登录页（使用 window.location 确保跳转成功）
            window.location.href = authStore.oidcLoginUrl
        }
    })
}
