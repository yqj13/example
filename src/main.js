import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import '@/assets/global.css'
import 'materialize-css/dist/css/materialize.css'
import 'materialize-css/dist/js/materialize'
import '@/assets/icon_font/iconfont.css'
import 'layui/dist/css/layui.css'
import 'layui/dist/layui'


Vue.use(ElementUI);
Vue.config.productionTip = false
document.addEventListener('DOMContentLoaded',function (){
  M.AutoInit();
});
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
