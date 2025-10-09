<template>
    <Breadcrumb>
        <BreadcrumbList>
            <BreadcrumbItem v-for="(item, index) in breadcrumbItems" :key="index">
                <BreadcrumbLink v-if="index < breadcrumbItems.length - 1" :href="item.path">
                    {{ t(item.label) }}
                </BreadcrumbLink>
                <BreadcrumbSeparator v-if="index < breadcrumbItems.length - 1" />
                <BreadcrumbPage v-else>
                    {{ t(item.label) }}
                </BreadcrumbPage>
            </BreadcrumbItem>
        </BreadcrumbList>
    </Breadcrumb>
</template>

<script setup lang="ts">
import { useI18n } from 'vue-i18n'
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
const { t } = useI18n()

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
        const label = menuItem ? menuItem.title : formatSegment(segment)

        breadcrumbs.push({
            label: label,
            path: path
        })
    })
    return breadcrumbs
})

function formatSegment(segment: string): string {
    return segment
        .split('-')
        .map(word => word.charAt(0).toUpperCase() + word.slice(1))
        .join(' ')
}
</script>
