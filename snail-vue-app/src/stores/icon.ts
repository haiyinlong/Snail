import { defineStore } from 'pinia'
import {
  AudioWaveform,
  Command,
  GalleryVerticalEnd,
  LayoutDashboard,
  ListTodo,
  Boxes,
  Users,
  Podcast,
  SquareUserRound,
  Bug,
  FileText,
  User,
  Palette,
  BellDot,
  CreditCard,
  BadgeHelp
} from 'lucide-vue-next'


export const useIconStore = defineStore('icon', () => {

  const icons =[{
    label: 'GalleryVerticalEnd',
    icon: GalleryVerticalEnd
  },{
    label: 'AudioWaveform',
    icon: AudioWaveform
  },{
    label: 'Command',
    icon: Command
  },{
    label: 'LayoutDashboard',
    icon: LayoutDashboard
  },{
    label: 'ListTodo',
    icon: ListTodo
  },{
    label: 'Boxes',
    icon: Boxes
  },{
    label: 'Users',
    icon: Users
  },{
    label: 'Podcast',
    icon: Podcast
  }]

  const iconMap = {
    // Team icons
    'GalleryVerticalEnd': GalleryVerticalEnd,
    'AudioWaveform': AudioWaveform,
    'Command': Command,
    // NavMain icons
    'LayoutDashboard': LayoutDashboard,
    'ListTodo': ListTodo,
    'Boxes': Boxes,
    'Users': Users,
    'Podcast': Podcast,
    'SquareUserRound': SquareUserRound,
    'Bug': Bug,
    'FileText': FileText,
    'User': User,
    'Palette': Palette,
    'BellDot': BellDot,
    'CreditCard': CreditCard,
    'BadgeHelp': BadgeHelp
  }

  const getIcon = (iconLabel: string) => {
      if (iconLabel && iconMap[iconLabel as keyof typeof iconMap]) {
        return iconMap[iconLabel as keyof typeof iconMap]
      }
      return iconLabel
  }

  const getDefaultIcon = () => {
      return GalleryVerticalEnd
  }
  return {
    icons,
    getIcon,
    getDefaultIcon,
  }
})