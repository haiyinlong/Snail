<script setup lang="ts">
import type { Table } from '@tanstack/vue-table'

import DataTableViewOptions from '@/components/data-table/view-options.vue'

import type { Menu } from '../data/schema'


interface DataTableToolbarProps {
  table: Table<Menu>
}

const props = defineProps<DataTableToolbarProps>()

</script>

<template>
  <div class="flex items-center justify-between">
    <DataTableViewOptions :table="props.table" />
  </div>
</template>
