import type { App } from 'vue'

import { setupAutoAnimate } from './auto-animate'
import { setupI18n } from './i18n'
import { setupNProgress } from './nprogress'
import { setupPinia } from './pinia'
import { setupTanstackVueQuery } from './tanstack-vue-query'

export function setupPlugins(app: App) {
  setupNProgress()
  setupAutoAnimate(app)
  setupTanstackVueQuery(app)
  setupI18n(app)
  setupPinia(app)
}
