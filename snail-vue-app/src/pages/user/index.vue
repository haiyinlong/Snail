<script setup lang="ts">
import { columns } from './components/columns'
import DataTable from './components/DataTable.vue'
import tasks from './data/tasks.json'

const handleQueryUpdate = (params: any) => {
    console.log('params', params)
}
</script>

<template>

    <div class="hidden h-full flex-1 flex-col space-y-6 md:flex">
        <div class="flex items-center justify-between space-y-2">
            <div>
                <h2 class="text-2xl font-bold tracking-tight">
                    任务列表
                </h2>
                <p class="text-muted-foreground">
                    任务清单
                </p>
            </div>
            <aside class="flex items-center gap-2 flex-wrap">
                操作按钮
            </aside>
        </div>
        <DataTable :data="tasks" :columns="columns" @update:query="handleQueryUpdate" />
    </div>
</template>