<template xmlns="http://www.w3.org/1999/html">
  <div>
    <!--    搜索表单-->
    <div style="margin-top: 9px; margin-bottom: 19px;display: flex; float: right;left: -19px">
      <el-input v-model="params.bookName" style="width: 199px;left: -19px"  placeholder="图书名称"></el-input>
      <select v-model="params.bookStyle" style="margin-left: 9px">
        <option value="" disabled selected style="color: grey">图书类型</option>
        <option value="国内小说">国内小说</option>
        <option value="国外小说">国外小说</option>2
        <option value="中外文学">中外文学</option>
        <option value="教辅资料">教辅资料</option>
      </select>
      <label style="margin-left: 9px;color: grey">Materialize Multiple Select</label>
<!--      <el-input v-model="params.bookStyle" style="width: 239px; margin-left: 5px" placeholder="图书类型"></el-input>-->
      <el-button style="margin-left: 5px;height: 39px" type="primary" @click="search" ><i class="el-icon-search"></i>搜索 </el-button>
    </div>

    <!--    表格-->
    <el-button v-if="showCheckbox" :size="'small'" @click="remove" type="danger" style="margin-left: 9%;margin-top: 69px;">确认删除</el-button>
    <el-button v-if="showCheckbox" :size="'small'" @click="cancel" style="margin-left: 3px;">取消</el-button>
    <el-table :data="tableData" stripe style="width: 80%;left: 9%;box-shadow:0 0 1px grey"
              @selection-change="handleSelectionChange">
      <el-table-column v-if="showCheckbox" type="selection" width="99"></el-table-column>
      <el-table-column fixed prop="id" label="ID" ></el-table-column>
      <el-table-column prop="bookName" label="图书名称" ></el-table-column><!--      width="139"-->
      <el-table-column prop="author" label="作者" ></el-table-column><!--      width="139"-->
      <el-table-column prop="bookStyle" label="图书类型" ></el-table-column><!--      width="139"-->
      <el-table-column prop="bookPrice" label="图书价格" ></el-table-column><!--      width="300"-->
      <!--      <el-table-column prop="zip" label="邮编" ></el-table-column>width="139"-->
    </el-table>
    <!--分页条-->
    <el-pagination
        @current-change = "handleCurrentChange"
        background
        layout = "prev, pager, next"
        :current-page= "params.page"
        :page-size = "params.pageSize"
        :total = this.total
        style="position: fixed; bottom: 19px">
    </el-pagination>
    <div class="fixed-action-btn">
      <a class="btn-floating btn-large red">
        <i class="iconfont icon-quanbu"></i>
      </a>
      <ul>
        <li><button class="btn-floating yellow tooltipped" data-position="top" data-tooltip="新建" @click="$router.push('/addBook')">
          <i class="iconfont icon-xinjian"></i>
        </button></li>
        <li><button class="btn-floating green tooltipped" data-position="bottom" data-tooltip="编辑" @click="editBookByBookName">
          <i class="iconfont icon-xiugai " ></i>
        </button></li>
        <li>
          <button class="btn-floating red darken-1 tooltipped" data-position="top" data-tooltip="删除" slot="reference" @click="deleteByID">
          <i class="iconfont icon-shanchu" ></i>
        </button>
        </li>
        <li><button class="btn-floating blue tooltipped" data-position="bottom" data-tooltip="上传" @click="upload">
          <i class="iconfont icon-shangchuan"></i>
        </button></li>
      </ul>
    </div>

<!--    模态框&#45;&#45;实现图书编辑操作-->
    <el-dialog title="编辑图书信息" :visible.sync="editFormVisible">
      <el-form :model="modelForm">
       <el-form-item>
         <input v-model="modelForm.bookName" id="name" type="text" class="validate" required  lay-verify="required" autocomplete="on">
         <label for="name" style="width: 99px;">图书名称</label>
         <input v-model="modelForm.author" id="author" type="text" class="validate" required  lay-verify="required" autocomplete="on">
         <label for="author" style="width: 99px;">图书作者</label>
        </el-form-item>
        <el-form-item>
          <input id="style" type="text" v-model="modelForm.bookStyle" autocomplete="on"></input>
          <label for="style">图书类型</label>
          <input id="price" type="number" v-model="modelForm.bookPrice" autocomplete="on"></input>
          <label for="price">图书价格</label>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </div>
    </el-dialog>
  </div>

</template>
<script>

import request from "@/utils/request";
import 'materialize-css/dist/css/materialize.css'
import 'materialize-css/dist/js/materialize'
import VTooltip from 'v-tooltip';
export default {
  name: 'BookView',
  data() {
    return {
      tableData: [],
      total : 0,
      params: {
        page : 1,
        pageSize : 10,
        bookName : '',
        bookStyle : '',
      },
      modelForm : {
        id: 1,
        bookName: '',
        author: '',
        bookStyle: '',
        bookPrice: 59
      },
      editFormVisible: false,
      showCheckbox: false,
      multipleSelection: []
    }
  },
  methods: {
    search() {
      request.get('/api/books', {
        params: this.params
      }).then(res => {
        // if (res.code === "1"){
        this.tableData = res.data.rows
        this.total = res.data.total
        // }
      })
      this.params = {
        page: 1,
        pageSize: 10,
        bookName: '',
        bookStyle: '',
      }
    },
    handleCurrentChange(page) {
      console.log(page);
      this.params.page = page;
      this.search();
    },
    editBookByBookName() {
    let i = prompt("请输入图书名称(唯一)：");
    if(i.trim() !== ""){
      this.modelForm.bookName = i.trim();
      request.get('/api/' + this.modelForm.bookName).then(res => {
        this.modelForm = res.data;
      })
      this.editFormVisible = true;
    }
    else{
      alert("图书编号不为空，请重新输入");
      this.editBookByBookName();
    }
    },
save(){
      this.editFormVisible = false;
      request.put('/api',this.modelForm).then(res =>{
        if(res.code === 1){
          this.search();
          let toastHTML = '<span>更新成功</span><button class="btn-flat toast-action">确认</button>';
          M.toast({html: toastHTML});
        }
        else{
          this.$notify.error(res.msg);
        }
      })
},
    deleteByID() {
      this.showCheckbox = true;
    },
    remove(){
      //根据ID删除表中内容
      if(this.multipleSelection.length === 0){
        this.$message.warning("warning: 找不到选定图书");
      }
      request.put('/api/remove',this.multipleSelection).then(res =>{
        if(res.code === 1){
          this.search();
          let toastHTML = '<span>删除成功</span><button class="btn-flat toast-action">确认</button>';
          M.toast({html: toastHTML});
        }
        else{
          this.$notify.error(res.msg);
        }
      })
      this.showCheckbox = false;
    },
    cancel(){
      this.showCheckbox = false;
      alert("删除操作取消");
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    upload() {
      request.post('/api/update').then(res =>{
        console.log(res);
      })
    }
    //自定义方法结束
  },


  //自动渲染页面
    created() {
      this.search()
    },

  //自动初始化组件
  mounted() {
    M.FloatingActionButton.init(document.querySelector('.fixed-action-btn'), {
      direction: 'top'
    });
    M.FormSelect.init(this.$el.querySelector('select'));
    M.Tooltip.init(document.querySelectorAll('.tooltipped'));
  }

}
</script>