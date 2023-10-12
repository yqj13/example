<template>
  <div>
    <div class="layui-collapse" lay-accordion>
      <div class="layui-colla-item">
        <div class="layui-colla-title">图书管理系统</div>
        <div class="layui-colla-content layui-show">
          <div class="layui-collapse" lay-accordion>
            <div class="layui-colla-item">
              <div class="layui-colla-title">图书管理</div>
              <div class="layui-colla-content layui-show">

                <div class="layui-collapse" lay-accordion>
                  <div class="layui-colla-item">
                    <div class="layui-colla-title">图书信息</div>
                    <div class="layui-colla-content layui-show">
                      根据图书名称、图书类型查询图书信息<br/>
                      可查询图书的编号、名称、分类、作者、价格等
                    </div>
                  </div>
                  <div class="layui-colla-item">
                    <div class="layui-colla-title">新建图书</div>
                    <div class="layui-colla-content">
                      <p>根据表单提示，完成表单中的图书编号（自动递增）、图书名称、图书作者、图书类型、图书价格（整型数字）<br/>
                      实现新建图书功能</p>
                    </div>
                  </div>
                  <div class="layui-colla-item">
                    <div class="layui-colla-title">图书编辑</div>
                    <div class="layui-colla-content">
                      <p>管理员点击“编辑按钮” -> 可供编辑的模态框弹出 -> 管理员根据对应图书绑定的ID修改表单内容<br/>
                      点击确认保存即可实现对图书信息的编辑</p>
                    </div>
                  </div>
                  <div class="layui-colla-item">
                    <div class="layui-colla-title">批量删除</div>
                    <div class="layui-colla-content">
                      <p>点击“删除”按钮 》勾选复选框 》确认删除<br/>
                      ---批量删除---</p>
                    </div>
                  </div>
                </div>

              </div>
            </div>
          </div>
  </div>
      </div>
    </div>
  </div>
</template>

<script>
import 'layui/dist/css/layui.css'
import 'layui/dist/layui'
export default {
  name: 'HomeView',

  mounted() {
    layui.use('element', function() {
      let element = layui.element;
      element.init();
    });
    }
}
</script>
