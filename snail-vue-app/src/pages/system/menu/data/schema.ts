import { z } from 'zod'

// Step 1: 先声明一个类型（可以是 let 或 let + as const）
let menuSchema: z.ZodType<Menu>

// Step 2: 定义接口（用于 z.infer）
export interface Menu {
  id: number
  name: string
  url?: string
  perms?: string
  type: number
  icon?: string
  orderNum?: number
  description?: string
  createTime?: string
  updateTime?: string
  parentId: number
  children?: Menu[] | undefined// 可选的子菜单
}

// Step 3: 定义 schema，使用 z.lazy 指向尚未完全定义的 menuSchema
menuSchema = z.object({
  id: z.number(),
  name: z.string(),
  url: z.string(),
  perms: z.string(),
  type: z.number(),
  icon: z.string(),
  orderNum: z.number(),
  description: z.string(),
  createTime: z.string(),
  updateTime: z.string(),
  parentId: z.number(),
  children: z.array(z.lazy(() => menuSchema)).optional(),
})

// Step 4: 导出 schema
export { menuSchema }