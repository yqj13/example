<template>
<div>
  <!--    头部区域-->
  <div style="height: 59px; line-height: 59px; background-color: white;margin-bottom: 3px">
    <img src="@/assets/logo.png" style="width: 39px; position: relative;  left: 19px">
    <span style="margin-left: 29px;font-size: 29px">图书管理系统</span>
  </div>

  <!--    侧边栏和主体-->
  <div style="display: flex; ">

    <!--  侧边导航栏-->
    <div style="width: 199px; overflow: hidden; min-height: calc(100vh - 62px); background-color: white ;margin-right: 3px">
      <el-menu :default-active="$route.path === '/' ? $route.path : $route.path.substring(1) " router :default-openeds="['/']" class="el-menu-demo">
        <el-menu-item index="/"><i class="iconfont icon-shouyefill"></i>首页</el-menu-item>
        <el-submenu index="/">
          <template slot="title"><i class="iconfont icon-yingyongguanli"></i>图书管理</template>
          <el-menu-item index="book"><i class="iconfont icon-taizhangguanli"></i>图书信息</el-menu-item>
          <el-menu-item index="addBook"><i class="iconfont icon-taizhangguanli"></i>新建图书</el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
    <!--  主体区域-->
    <div style="flex: 1; background-color: white">
      <router-view/>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "LayoutView"
}
</script>

<style scoped>

</style>