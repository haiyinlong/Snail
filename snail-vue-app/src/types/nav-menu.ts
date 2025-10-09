export interface TeamData {
  name: string;
  logo: string; // 使用字符串类型而不是组件对象
  plan: string;
}

export interface NavMainData {
  title: string;
  url?: string;
  icon?: string; // 使用字符串类型而不是组件对象
  items?: (NavMainData)[];
}

export interface MenuBarData {
  title: string;
  url: string;
}