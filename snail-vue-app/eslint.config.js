import antfu from '@antfu/eslint-config'
import pluginQuery from '@tanstack/eslint-plugin-query'

export default antfu({
  ignores: [
    '**/node_modules/**',
    '**/dist/**',
    '**/build/**',
    '**/lib/**',
    '**/components/ui/**',
  ],
  settings: {
    'import/core-modules': ['vue-router/auto-routes'],
  },
  globals: {
    definePage: 'readonly',
  },
  rules: {
    'perfectionist/sort-imports': ['error', {
      tsconfigRootDir: '.',
    }],
    'perfectionist/sort-named-imports': ['error', {
      // 禁用命名导入的字母排序
      order: 'ignore',
      // 或者自定义排序行为
      groups: [
        ['builtin', 'external'],
        'internal',
        ['parent', 'sibling', 'index'],
      ],
    }],
  },
  ...pluginQuery.configs['flat/recommended'],
})
