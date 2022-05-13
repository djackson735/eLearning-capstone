import Vue from 'vue'
import Router from 'vue-router'
import store from '../store/index'

import Home from '../views/Home.vue'
import Login from '../views/access/Login.vue'
import Logout from '../views/access/Logout.vue'
import Register from '../views/access/Register.vue'

import Course from '../views/course/Course.vue'
import AddCurricula from '../views/course/AddCurricula.vue'
import EditCurricula from '../views/course/EditCurricula.vue'
// import CurriculaDetail from '../components/course/CurriculaDetail.vue'

import CurriculaView from '../views/course/Curricula'
import SideNav from "@/components/course/SideNav";
import CourseEditor from "@/views/course/EditCourse";
import UserElement from "@/components/user/UserElement";


import UsersView from "../views/admin/UsersView";
import AdminHome from "../views/admin/AdminHome";
import CourseDirectory from "@/views/course/CourseDirectory";
import EnrolledCourseDirectory from "@/views/course/EnrolledCourseDirectory";




Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/courses',
      name: "courses",
      component: SideNav,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/courses/:id',
      name: 'Course',
      component: Course,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: '/curricula/:curriculaID',
      name: 'Curriculaview',
      component: CurriculaView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/courses/:courseID/curricula/create',
      name: 'AddCurricula',
      component: AddCurricula,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/courses/:courseID/curricula/:curriculaID/edit',
      name: 'EditCurricula',
      component: EditCurricula,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/admin',
      name: 'admin',
      component: AdminHome,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/admin/user-list",
      name: "adminUserList",
      component: UsersView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/admin/:id",
      name: "AdminDetail",
      component: UserElement,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/content",
      name: "Directory",
      component: CourseDirectory,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/course/:id/edit",
      name: "CourseEditor",
      component: CourseEditor,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/enrollments",
      name: "Enrollments",
      component: EnrolledCourseDirectory,
      meta: {
        requiresAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    next();
  }
});
export default router;