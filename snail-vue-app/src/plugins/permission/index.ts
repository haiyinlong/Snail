// src/directives/permission.ts
import { useAuthStore } from '@/stores/auth'
import type { Directive } from 'vue'

export const permission: Directive = {
  mounted(el, binding) {
    const { value } = binding
    const authStore = useAuthStore()
    
    if (!value) return
    
    // Fix: Use readonly array type or create a mutable copy
    const permissions: readonly string[] = authStore.user?.permissions || []
    const requiredPermissions = Array.isArray(value) ? value : [value]
    
    const hasPermission = requiredPermissions.some(permission => 
      permissions.includes(permission)
    )
    
    if (!hasPermission) {
      el.style.display = 'none'
    }
  }
}

export default permission