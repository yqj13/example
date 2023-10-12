<template>
  <div>
    <h3 style="text-align: center; color: #01b4ff">图书管理系统</h3>
<div class="box">
  <div class="content">
    <h3>登录</h3>
    <div>
      <input type="text" placeholder="请输入用户名" style="border-bottom: none;outline: none" v-model="message.userName">
    </div>
    <div>
      <input type="password" placeholder="请输入密码" style="border-bottom: none;outline: none" v-model="message.password">
    </div>
    <div>
      <input type="submit" value="登录" @click="login">
    </div>
  </div>
  <a href="#" class="btns" @click="resetPassWord">忘记密码</a>
  <a href="http://localhost:8081/logon" class="btns register">注册</a>
</div>
  </div>
</template>
<script>
import '@/assets/Login.css'
import request from "@/utils/request";

export default {
  name: 'LoginView',
  data() {
 return {
   message: {
     userName: '',
     password: '',
     phone: ''
   }
 }
  },
  methods: {
    login(){
      if (this.message.userName !== ''&&this.message.password !== ''){
        request.post('/login',this.message).then(res =>{
          console.log(res)
          if(res.code === 1){
            this.$message.success("登录成功");
            this.$router.push('/library');
          }
          else if(res.code === 0){
            // console.log('用户名或密码错误');
            this.$message.error("用户名或密码错误")
          }
        })
      }
      else {
        // console.log('用户名和密码不为空');
        this.$message.warning("用户名和密码不为空")
        this.message = {
          userName: '',
          password: '',
          phone: ''
        }
      }
    },
    resetPassWord(){

    }
  }

}
</script>