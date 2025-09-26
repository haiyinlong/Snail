// 配置路由信息
import { createRouter, createWebHistory} from 'vue-router';
import { createRouterGuard } from './guard'

// 改为动态导入
var routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/pages/dashboard/index.vue'),
  },
  {
    path: '/user',
    name: 'User',
    component: () => import('@/pages/user/index.vue'),
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/pages/login/index.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,

  scrollBehavior() {
    return { left: 0, top: 0, behavior: 'smooth' }
  },
})

createRouterGuard(router)

export default router
