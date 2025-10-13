import type { ColumnDef } from '@tanstack/vue-table'
import { h } from 'vue'

import DataTableColumnHeader from '@/components/data-table/column-header.vue'
import { SelectColumn } from '@/components/data-table/table-columns'
import { Badge } from '@/components/ui/badge'
import { ChevronDown, ChevronRight } from 'lucide-vue-next'
import { useIconStore  } from '@/stores/icon'

import {menuType} from '@/pages/system/menu/data/data.ts'
import type { Menu } from '../data/schema'
import DataTableRowActions from './data-table-row-actions.vue'
import { useI18n } from 'vue-i18n'

const iconStore = useIconStore()

export const columns: ColumnDef<Menu>[] = [
   SelectColumn as ColumnDef<Menu>,
  {
    id: 'select',
    header: ({ table }) => h('div', {
      class: 'flex items-center justify-center'
    }, [
      h('input', {
        type: 'checkbox',
        checked: table.getIsAllRowsSelected(),
        'onUpdate:checked': (value: boolean) => table.toggleAllRowsSelected(!!value),
        class: 'w-4 h-4 rounded border-gray-300'
      })
    ]),
    cell: ({ row }) => h('div', {
      class: 'flex items-center justify-center'
    }, [
      h('input', {
        type: 'checkbox',
        checked: row.getIsSelected(),
        'onUpdate:checked': (value: boolean) => row.toggleSelected(!!value),
        class: 'w-4 h-4 rounded border-gray-300 disabled:opacity-50'
      })
    ]),
    enableSorting: false,
    enableHiding: false,
  },
  {
    accessorKey: 'name',
    header: ({ column }) =>{
      const { t } = useI18n()
      return h(DataTableColumnHeader<Menu>, { column, title: t('system.menu.table.name') })
    },
    cell: ({ row }) => {
      const canExpand = row.getCanExpand()
      const isExpanded = row.getIsExpanded()

      return h('div', {
        class: 'flex items-center max-w-[300px] truncate',
        style: { paddingLeft: `${row.depth * 24 + 12}px` }
      }, [
        canExpand
          ? h('button', {
              class: 'mr-1 focus:outline-none',
              onClick: () => row.toggleExpanded()
            }, [
              isExpanded
                ? h(ChevronDown, { class: 'h-4 w-4' })
                : h(ChevronRight, { class: 'h-4 w-4' })
            ])
          : h('div', { class: 'w-6' }), 
        h('span', { class: 'truncate font-medium' }, row.getValue('name'))
      ])
    },
    enableSorting: false,
    enableHiding: false,
  },
  {
    accessorKey: 'description',
    header: ({ column }) => {
      const { t } = useI18n()
      return h(DataTableColumnHeader<Menu>, { column, title: t('system.menu.table.description') })
    },
    cell: ({ row }) => {
      return  h('span', { class: 'max-w-[200px] truncate' }, row.getValue('description'))
    },
    enableSorting: false,
  },
  {
    accessorKey: 'url',
    header: ({ column }) =>  {
      const { t } = useI18n()
      return h(DataTableColumnHeader<Menu>, { column, title: t('system.menu.table.url') })
    },
    cell: ({ row }) => {
      return h('div', { class: 'max-w-[200px] truncate' }, row.getValue('url'))
    },
    enableSorting: false,
  },
{
  accessorKey: 'perms',
  header: ({ column }) => {
      const { t } = useI18n()
      return  h(DataTableColumnHeader<Menu>, { column, title: t('system.menu.table.permission') })
  },
  cell: ({ row }) => {
    return h('span', { class: 'text-xs bg-accent px-2 py-1 rounded' }, String(row.getValue('perms')))
  },
  enableSorting: false,
},
 {
    accessorKey: 'type',
    header: ({ column }) =>  {
      const { t } = useI18n()
      return h(DataTableColumnHeader<Menu>, { column, title: t('system.menu.table.type') })
    },
    cell: ({ row }) => {
      const { t } = useI18n()
      const menuTypeEnum =  menuType.find(item => item.value == row.original.type)
              
      return h('span', {}, menuTypeEnum ? h(Badge, {variant: "outline", style: { color: menuTypeEnum.color, borderColor: menuTypeEnum.color } }, () => t(menuTypeEnum?.label)) : '')
    },
    enableSorting: false,
  },
  {
    accessorKey: 'icon',
    header: ({ column }) => {
      const { t } = useI18n()
      return  h(DataTableColumnHeader<Menu>, { column, title: t('system.menu.table.icon') })
    },
    cell: ({ row }) => {
      if(row.getValue('icon')){
        return h('code', { class: 'text-xs'}, h(iconStore.getIcon(row.getValue('icon'))))
      }
      return h('code', { class: 'text-xs'}, '-')
    },
    enableSorting: false,
  },
  {
    id: 'actions',
    cell: ({ row }) => {
      return h(DataTableRowActions, { row })
    },
  },
]