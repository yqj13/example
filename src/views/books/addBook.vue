<template>
  <div>
      <h5 style="margin-top: 69px;margin-left: 299px;">新建图书</h5>
    <form class="layui-form layui-form-pane" :model="form" action="" style="margin-top: 39px;margin-left: 299px;">
      <div class="row "style="margin-top: 29px">
        <div class="input-field col s3">
          <input  id="disabled" type="text" class="validate" disabled="disabled">
          <label for="disabled">图书编号</label>
          <span style="margin-left: 9px;font-size: 9px;color: grey">此处不可填写</span>
        </div>
        <div class="input-field col s3" style="margin-left: 19px">
          <input v-model="form.bookName" id="name" type="text" class="validate" required  lay-verify="required" autocomplete="off">
          <label for="name">图书名称</label>
        </div>
      </div>
      <div class="row " style="margin-top: 29px">
        <div class="input-field col s3">
          <input v-model="form.author" id="author" type="text" class="validate">
          <label for="author">图书作者</label>
        </div>
        <div class="input-field col s3" style="margin-left: 19px">
          <select v-model="form.bookStyle">
            <option value="国内小说">国内小说</option>
            <option value="国外小说">国外小说</option>
            <option value="中外文学">中外文学</option>
            <option value="教辅资料">教辅资料</option>
          </select>
          <label>图书类型</label>
        </div>
      </div>
      <div class="row" style="margin-top: 29px;margin-left: 9px">
        <label style="font-size: 16px;line-height: 13px;">图书价格</label>
        <div class="layui-input-inline" style="width: 239px;">
          <input v-model="form.bookPrice" type="number" name="price_min" placeholder="" autocomplete="off" class="layui-input" min="0" step="1" lay-affix="number" style="margin-left: 9px">
        </div>
        <span style="margin-left: 9px;font-size: 9px;color: grey">此处输入数字</span>
      </div>
    </form>
    <div style="margin-top: 9px;text-align: center">
      <el-button type="primary" @click="save">提交</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>
  </div>
</template>
<script>

import request from "@/utils/request";

export default {
  name:'AddBookView',
    data() {
      return{
        form: {
          bookName: '',
          author: '',
          bookStyle: '',
          bookPrice: 59
        }
    }
  },
  methods :{
    save(){
      request.post('/api',this.form).then(res => {
        if (res.code === 1){
          let toastHTML = '<span>新建完成</span><button class="btn-flat toast-action">确认</button>';
          M.toast({html: toastHTML});
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    reset(){
      this.form = {
        bookName: '',
        author: '',
        bookStyle: '',
        bookPrice: 59
      }
    }
  },
  mounted() {
    M.FormSelect.init(this.$el.querySelector('select'));
  }
}
</script>