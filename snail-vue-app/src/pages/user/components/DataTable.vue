<script setup lang="ts">
import type {
    ColumnDef,
    ColumnFiltersState,
    SortingState,
    VisibilityState,
    PaginationState,
} from '@tanstack/vue-table'
import type { Task } from '../data/schema'

import {
    FlexRender,
    getCoreRowModel,
    getFacetedRowModel,
    getFacetedUniqueValues,
    getFilteredRowModel,
    getPaginationRowModel,
    getSortedRowModel,
    useVueTable,
} from '@tanstack/vue-table'
import { ref } from 'vue'
import { valueUpdater } from '@/lib/utils'
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from '@/components/ui/table'
import DataTablePagination from './DataTablePagination.vue'
import DataTableToolbar from './DataTableToolbar.vue'

interface DataTableProps {
    columns: ColumnDef<Task, any>[]
    data: Task[]
}
const props = defineProps<DataTableProps>()

const sorting = ref<SortingState>([])
const columnFilters = ref<ColumnFiltersState>([])
const columnVisibility = ref<VisibilityState>({})
const pagination = ref<PaginationState>({
    pageIndex: 0,
    pageSize: 10,
})
const rowSelection = ref({})

// TODO 定义查询 api 方法

const table = useVueTable({
    get data() { return props.data },
    get columns() { return props.columns },
    state: {
        // 添加排序、过滤、可见列、行选择状态，帮绑定到 vue refs 中给 api 使用
        get sorting() { return sorting.value },
        get columnFilters() { return columnFilters.value },
        get columnVisibility() { return columnVisibility.value },
        get rowSelection() { return rowSelection.value },
        get pagination() { return pagination.value },
    },
    enableRowSelection: true,
    onSortingChange: updaterOrValue => valueUpdater(updaterOrValue, sorting),
    onColumnFiltersChange: updaterOrValue => valueUpdater(updaterOrValue, columnFilters),
    onColumnVisibilityChange: updaterOrValue => valueUpdater(updaterOrValue, columnVisibility),
    onRowSelectionChange: updaterOrValue => valueUpdater(updaterOrValue, rowSelection),
    onPaginationChange: updaterOrValue => valueUpdater(updaterOrValue, pagination),
    getCoreRowModel: getCoreRowModel(), //返回一个基本的行模型，该模型只是传递给表的原始数据的 1：1 映射
    manualFiltering: true,
    // getFilteredRowModel: getFilteredRowModel(),  // manualFiltering: true 时不生效，客户端过滤。返回考虑列过滤和全局过滤的行模型。
    getPaginationRowModel: getPaginationRowModel(), // 返回一个行模型，该模型仅包含根据分页状态应在当前页面上显示的行。
    getSortedRowModel: getSortedRowModel(),  // 返回已应用排序的行模型。
    getFacetedRowModel: getFacetedRowModel(),
    getFacetedUniqueValues: getFacetedUniqueValues(),
})
</script>

<template>
    <div class="space-y-4">
        <div>
            <!-- 预览展示 columnFilters、columnVisibility、sorting、rowSelection 的值-->
            columnFilters: <span> {{ columnFilters }}</span><br />
            columnVisibility:<span> {{ columnVisibility }}</span><br />
            sorting: <span> {{ sorting }}</span><br />
            rowSelection: <span> {{ rowSelection }}</span><br />
            pagination: <span> {{ pagination }}</span>
        </div>
        <DataTableToolbar :table="table" />
        <div class="rounded-md border">
            <Table>
                <TableHeader>
                    <TableRow v-for="headerGroup in table.getHeaderGroups()" :key="headerGroup.id">
                        <TableHead v-for="header in headerGroup.headers" :key="header.id">
                            <FlexRender v-if="!header.isPlaceholder" :render="header.column.columnDef.header"
                                :props="header.getContext()" />
                        </TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    <template v-if="table.getRowModel().rows?.length">
                        <TableRow v-for="row in table.getRowModel().rows" :key="row.id"
                            :data-state="row.getIsSelected() && 'selected'">
                            <TableCell v-for="cell in row.getVisibleCells()" :key="cell.id">
                                <FlexRender :render="cell.column.columnDef.cell" :props="cell.getContext()" />
                            </TableCell>
                        </TableRow>
                    </template>

                    <TableRow v-else>
                        <TableCell :colspan="columns.length" class="h-24 text-center">
                            No results.
                        </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </div>

        <DataTablePagination :table="table" />
    </div>
</template>