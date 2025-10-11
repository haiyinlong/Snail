<script lang="ts" setup>
import type { LabelProps } from 'reka-ui'
import type { HTMLAttributes } from 'vue'
import { Label } from '@/components/ui/label'
import { cn } from '@/lib/utils'
import { useFormField } from './useFormField'

const props = defineProps<{
  class?: HTMLAttributes['class']
  required?: boolean
}>()
const { error, formItemId } = useFormField()
</script>

<template>
  <Label data-slot="form-label" :data-error="!!error" :class="cn(
    'data-[error=true]:text-destructive',
    props.class,
  )" :for="formItemId">
    <slot />
    <span v-if="props.required" class="text-destructive"> *</span>
  </Label>
</template>
