<template>
<div>
  <h3 style="text-align: center; color: #01b4ff">图书管理系统</h3>
  <div class="box">
    <div class="content">
      <h3>注册</h3>
      <div>
        <input type="text" placeholder="请输入用户名" style="border-bottom: none;outline: none" v-model="messages.userName">
      </div>
      <div>
        <input type="text" placeholder="请填写手机号" style="border-bottom: none;outline: none" v-model="messages.phone">
      </div>
      <div>
        <input type="password" placeholder="请输入密码" style="border-bottom: none;outline: none" v-model="messages.password">
      </div>
      <div>
        <input type="submit" value="注册" @click="logon">
      </div>
    </div>
    <a href="http://localhost:8081/login" class="btns" style="background-color: cyan">登录</a>
</div>
</div>
</template>

<script>
import '@/assets/Login.css'
import request from "@/utils/request";
export default {
  name: "LogonView",
  data(){
    return {
      messages: {
        userName: '',
        password: '',
        phone: ''
      }
    }
  },
  methods: {
    logon() {
      if (this.messages.userName !== '' && this.messages.password !== '') {
        request.post('/login', this.messages).then(res => {
          console.log(res)
          if (res.code === 1) {
            this.$message.warning("不能重复创建");
          } else if (res.code === 0) {
            request.post('/logon', this.messages).then(res => {
              console.log(res)
              if (res.code === 1) {
                this.$message.success("注册成功");
              } else if (res.code === 0) {
                this.$message.error("失败，用户名和密码不为空");
              }
            })
          }
        })
      }
    }
  }
}



</script>

<style scoped>

</style>