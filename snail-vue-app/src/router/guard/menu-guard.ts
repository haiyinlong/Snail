import type { Router } from 'vue-router'
import { useMenuStore } from '@/stores/menu'

export function menuGuard(router: Router) {
    // 全局前置守卫
    router.beforeEach(async (to, from, next) => {
        const menuStore = useMenuStore()
        
        // 如果还没有获取过菜单数据，则获取
        if (!menuStore.menus.length) {
            await menuStore.fetchUserMenus()
        }
        next()
    })
}
