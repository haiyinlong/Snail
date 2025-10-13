<script lang="ts" setup>
import { toTypedSchema } from '@vee-validate/zod'
import { useForm } from 'vee-validate'
import { toast } from 'vue-sonner'
import * as z from 'zod'
import type { Menu } from '../data/schema'
import { FormField } from '@/components/ui/form'
import { menuType } from '../data/data'
import { useIconStore } from '@/stores/icon'
import { useI18n } from 'vue-i18n'
import { computed, h } from 'vue'
import { useMenuManagementStore } from '@/stores/menu-management'

const { t } = useI18n()
const props = defineProps<{
  menu: Menu | null
}>()
defineEmits(['close'])

const iconStore = useIconStore()
const menuManagementStore = useMenuManagementStore()

const menu = computed(() => props.menu)
const title = computed(() => menu.value?.id ? t('system.menu.form.title.edit') : t('system.menu.form.title.create'))
const description = computed(() => menu.value?.id ? t('system.menu.form.title.editDescription') + menu.value.id : t('system.menu.form.title.createDescription'))

const formSchema = toTypedSchema(z.object({
  name: z.string(),
  url: z.string(),
  perms: z.string(),
  type: z.coerce.number(),
  icon: z.string(),
  orderNum: z.coerce.number().optional().default(0),
  description: z.string(),
  parentId: z.coerce.number().optional().default(0),
}))

const initialValues = computed(() => {
  if (menu.value) {
    return menu
  }
  return {}
})

const { isFieldDirty, handleSubmit } = useForm({
  validationSchema: formSchema,
  initialValues: initialValues.value,
})

const onSubmit = handleSubmit(async (values) => {
  // 处理提交
  toast('You submitted the following values:', {
    description: h('pre', { class: 'mt-2 w-[340px] rounded-md bg-slate-950 p-4' }, h('code', { class: 'text-white' }, JSON.stringify(values, null, 2))),
  })
  try {
    await menuManagementStore.addMenu(values as Menu)
    // emit('close') // 关闭弹窗
  } catch (error) {
    console.error('Failed to add menu:', error)
    throw error
  }
})
</script>

<template>
  <div>
    <UiDialogHeader>
      <UiDialogTitle>
        {{ title }}
      </UiDialogTitle>
      <UiDialogDescription>
        {{ description }}
      </UiDialogDescription>
    </UiDialogHeader>
    <form id="dialogForm" class="space-y-6 max-h-[70vh] mt-4" @submit.prevent="onSubmit">
      <FormField v-slot="{ componentField }" name="type" :validate-on-blur="!isFieldDirty">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">
            {{ $t('system.menu.form.type') }}
          </UiFormLabel>
          <div class="flex-1">

            <UiFormControl>
              <UiSelect v-bind="componentField">
                <UiSelectTrigger class="w-1/1">
                  <UiSelectValue :placeholder="$t('system.menu.form.typePlaceholder')" />
                </UiSelectTrigger>
                <UiSelectContent>
                  <UiSelectGroup>
                    <UiSelectItem v-for="menuItem in menuType" :key="menuItem.label" :value="menuItem.value">
                      {{ $t(menuItem.label) }}
                    </UiSelectItem>
                  </UiSelectGroup>
                </UiSelectContent>
              </UiSelect>
            </UiFormControl>
            <UiFormDescription />
            <UiFormMessage />
          </div>
        </UiFormItem>
      </FormField>

      <FormField v-slot="{ componentField }" name="name" :validate-on-blur="!isFieldDirty">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">
            {{ $t('system.menu.form.name') }}
          </UiFormLabel>
          <div class="flex-1">
            <UiFormControl>
              <UiInput type="text" :placeholder="$t('system.menu.form.namePlaceholder')" v-bind="componentField" />
            </UiFormControl>
            <UiFormDescription />
            <UiFormMessage />
          </div>
        </UiFormItem>
      </FormField>

      <FormField v-slot="{ componentField }" name="description" :validate-on-blur="!isFieldDirty">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">
            {{ $t('system.menu.form.description') }}
          </UiFormLabel>
          <div class="flex-1">
            <UiFormControl>
              <UiInput type="text" :placeholder="$t('system.menu.form.descriptionPlaceholder')"
                v-bind="componentField" />
            </UiFormControl>
            <UiFormDescription />
            <UiFormMessage />
          </div>
        </UiFormItem>
      </FormField>

      <FormField v-slot="{ componentField }" name="url" :validate-on-blur="!isFieldDirty">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">
            {{ $t('system.menu.form.url') }}
          </UiFormLabel>
          <div class="flex-1">
            <UiFormControl>
              <UiInput type="text" :placeholder="$t('system.menu.form.urlPlaceholder')" v-bind="componentField" />
            </UiFormControl>
            <UiFormDescription />
            <UiFormMessage />
          </div>
        </UiFormItem>
      </FormField>

      <FormField v-slot="{ componentField }" name="perms" :validate-on-blur="!isFieldDirty">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">
            {{ $t('system.menu.form.permission') }}
          </UiFormLabel>
          <div class="flex-1">
            <UiFormControl>
              <UiInput type="text" :placeholder="$t('system.menu.form.permissionPlaceholder')"
                v-bind="componentField" />
            </UiFormControl>
            <UiFormDescription />
            <UiFormMessage />
          </div>
        </UiFormItem>
      </FormField>

      <FormField v-slot="{ componentField }" name="icon" :validate-on-blur="!isFieldDirty">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">
            {{ $t('system.menu.form.icon') }}
          </UiFormLabel>
          <div class="flex-1">
            <UiFormControl>
              <UiSelect v-bind="componentField">
                <UiSelectTrigger class="w-1/1">
                  <UiSelectValue>
                    <component :is="iconStore.getIcon(componentField.modelValue)" class="size-4 shrink-0" />
                    {{ componentField.modelValue ?componentField.modelValue : $t('system.menu.form.iconPlaceholder') }}
                  </UiSelectValue>
                </UiSelectTrigger>
                <UiSelectContent>
                  <UiSelectGroup>
                    <UiSelectItem v-for="menuItem in iconStore.icons" :key="menuItem.label" :value="menuItem.label">
                      <component :is="menuItem.icon" class="size-4 shrink-0" />
                      {{ menuItem.label }}
                    </UiSelectItem>
                  </UiSelectGroup>
                </UiSelectContent>
              </UiSelect>
            </UiFormControl>
            <UiFormDescription />
            <UiFormMessage />
          </div>
        </UiFormItem>
      </FormField>
    </form>
    <UiDialogFooter>
      <UiButton type="submit" form="dialogForm" class="mt-5">
        {{ $t('common.saveChanges') }}
      </UiButton>
    </UiDialogFooter>
  </div>
</template>

<style scoped></style>
