import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LayoutView from '../views/Layout.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/library',
    name: 'Layout',
    component: LayoutView,
    redirect: '/home',
    children: [
      {
        path: '/home',
        name: 'Home',
        component: HomeView
      },
      {
        path: '/book',
        name: 'Book',
        component: () => import('../../src/views/books/book.vue')
      },
      {
        path: '/addBook',
        name: 'AddBook',
        component: () => import('../../src/views/books/addBook.vue')
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../../src/views/login/Login.vue')
  },
  {
    path: '/logon',
    name: 'Logon',
    component: () => import('../../src/views/login/Logon.vue')
  }


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
