<script lang="ts" setup>
import { toTypedSchema } from '@vee-validate/zod'
import { useForm } from 'vee-validate'
import { toast } from 'vue-sonner'
import * as z from 'zod'
import type { Task } from '../data/schema'
import { FormField } from '@/components/ui/form'
import { labels, priorities, statuses } from '../data/data'

const props = defineProps<{
  task: Task | null
}>()
defineEmits(['close'])

const task = computed(() => props.task)
const title = computed(() => task.value?.id ? `Edit Task` : 'Create Task')
const description = computed(() => task.value?.id ? `Edit task ${task.value.id}` : 'Create new task')

const formSchema = toTypedSchema(z.object({
  title: z.string().min(2).max(50),
  status: z.string(),
  label: z.string(),
  priority: z.string(),
}))

const initialValues = computed(() => {
  if (task.value) {
    return {
      title: task.value.title || '',
      status: task.value.status || '',
      label: task.value.label || '',
      priority: task.value.priority || ''
    }
  }
  return {}
})

const { isFieldDirty, handleSubmit } = useForm({
  validationSchema: formSchema,
  initialValues: initialValues.value,
})

const onSubmit = handleSubmit((values) => {
  // 处理提交
  toast('You submitted the following values:', {
    description: h('pre', { class: 'mt-2 w-[340px] rounded-md bg-slate-950 p-4' }, h('code', { class: 'text-white' }, JSON.stringify(values, null, 2))),
  })
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

    <form id="dialogForm" class="space-y-6 max-h-[70vh] mt-4" @submit="onSubmit">
      <FormField v-slot="{ componentField }" name="title" :validate-on-blur="!isFieldDirty">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">
            {{ $t('common.title') }}
          </UiFormLabel>
          <div class="flex-1">
            <UiFormControl>
              <UiInput type="text" placeholder="shadcn" v-bind="componentField" />
            </UiFormControl>
            <UiFormDescription />
            <UiFormMessage />
          </div>
        </UiFormItem>
      </FormField>

      <FormField v-slot="{ componentField }" name="status" :validate-on-blur="!isFieldDirty">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">
            {{ $t('common.status') }}
          </UiFormLabel>
          <div class="flex-1">
            <UiFormControl>
              <UiSelect v-bind="componentField">
                <UiSelectTrigger class="w-1/1">
                  <UiSelectValue :placeholder="$t('common.selectStatus')" />
                </UiSelectTrigger>
                <UiSelectContent>
                  <UiSelectGroup>
                    <UiSelectItem v-for="status in statuses" :key="status.value" :value="status.value">
                      <component :is="status.icon" class="size-4 shrink-0" />
                      {{ status.label }}
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

      <FormField v-slot="{ componentField }" name="label">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">{{ $t('common.label') }}</UiFormLabel>
          <div class="flex-1">
            <UiFormControl>
              <UiRadioGroup v-bind="componentField">
                <UiFormItem v-for="label in labels" :key="label.value" class="flex items-center space-y-0 gap-x-3">
                  <UiFormControl>
                    <UiRadioGroupItem :value="label.value" />
                  </UiFormControl>
                  <UiFormLabel class="font-normal">
                    {{ label.label }}
                  </UiFormLabel>
                </UiFormItem>
              </UiRadioGroup>
            </UiFormControl>
            <UiFormDescription />
            <UiFormMessage />
          </div>
        </UiFormItem>
      </FormField>

      <FormField v-slot="{ componentField }" name="priority">
        <UiFormItem class="flex flex-row">
          <UiFormLabel :required="true" class="w-1/4">{{ $t('common.priority') }}</UiFormLabel>
          <div class="flex-1">
            <UiFormControl>
              <UiRadioGroup v-bind="componentField">
                <UiFormItem v-for="priority in priorities" :key="priority.value"
                  class="flex items-center space-y-0 gap-x-3">
                  <UiFormControl>
                    <UiRadioGroupItem :value="priority.value" />
                  </UiFormControl>
                  <UiFormLabel class="font-normal">
                    {{ priority.label }}
                  </UiFormLabel>
                </UiFormItem>
              </UiRadioGroup>
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
