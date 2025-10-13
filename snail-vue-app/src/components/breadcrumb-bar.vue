<template>
    <Breadcrumb>
        <BreadcrumbList>
            <BreadcrumbItem v-for="(item, index) in breadcrumbItems" :key="index">
                <BreadcrumbLink v-if="index < breadcrumbItems.length - 1" :href="item.path">
                    {{ $t(item.label) }}
                </BreadcrumbLink>
                <BreadcrumbSeparator v-if="index < breadcrumbItems.length - 1" />
                <BreadcrumbPage v-else>
                    {{ $t(item.label) }}
                </BreadcrumbPage>
            </BreadcrumbItem>
        </BreadcrumbList>
    </Breadcrumb>
</template>

<script setup lang="ts">
import {
    Breadcrumb,
    BreadcrumbItem,
    BreadcrumbLink,
    BreadcrumbList,
    BreadcrumbPage,
    BreadcrumbSeparator,
} from "@/components/ui/breadcrumb"
import { useRoute } from 'vue-router'
import { computed } from 'vue'
import type { MenuBarData } from '@/types/nav-menu'

const route = useRoute()

const menus = localStorage.getItem('menuBarData') || ''
const menuItems = JSON.parse(menus) as MenuBarData[]

const breadcrumbItems = computed(() => {
    const pathArray = route.path.split('/').filter(item => item)

    const breadcrumbs = [{
        label: 'common.home',
        path: '/'
    }]

    let path = ''
    pathArray.forEach(segment => {
        path += '/' + segment

        const menuItem = menuItems.find(item => item.url === path)
        if (menuItem) {
            breadcrumbs.push({
                label: menuItem.title,
                path: path
            })
        }
    })
    return breadcrumbs
})

</script>
