import {
  createRouter,
  createWebHashHistory
} from "vue-router";
import Home from "../views/Home.vue";

const routes = [{
    path: '/',
    redirect: '/dashboard'
  }, {
    path: "/",
    name: "Home",
    component: Home,
    children: [{
      path: "/dashboard",
      name: "dashboard",
      meta: {
        title: '系统首页'
      },
      component: () => import( /* webpackChunkName: "dashboard" */ "../views/Dashboard.vue")
    }, {
      path: "/table",
      name: "basetable",
      meta: {
        title: '表格'
      },
      component: () => import( /* webpackChunkName: "table" */ "../views/BaseTable.vue")
    }, {
      path: "/tableM",
      name: "basetableM",
      meta: {
        title: '素材列表'
      },
      component: () => import( /* webpackChunkName: "table" */ "../views/MaterialTable.vue")
    }, {
      path: "/tableP",
      name: "basetableP",
      meta: {
        title: '发布节目'
      },
      component: () => import( /* webpackChunkName: "table" */ "../views/ProgramTable.vue")
    }, {
      path: "/programadd",
      name: "programadd",
      meta: {
        title: '新建节目'
      },
      component: () => import( /* webpackChunkName: "login" */ "../views/ProgramAdd.vue")
    },{
      path: "/tableN",
      name: "basetableN",
      meta: {
        title: '发布公告'
      },
      component: () => import( /* webpackChunkName: "table" */ "../views/NoticeList.vue")
    }, {
      path: "/noticeadd",
      name: "noticeadd",
      meta: {
        title: '新建公告'
      },
      component: () => import( /* webpackChunkName: "login" */ "../views/NoticeAdd.vue")
    },{
      path: "/tablePlan",
      name: "basetablePlan",
      meta: {
        title: '计划列表'
      },
      component: () => import( /* webpackChunkName: "table" */ "../views/PlanList.vue")
    }, {
      path: "/tablePlanM",
      name: "basetablePlanM",
      meta: {
        title: '计划审核'
      },
      component: () => import( /* webpackChunkName: "table" */ "../views/PlanListManager.vue")
    },
    {
      path: "/planadd",
      name: "planadd",
      meta: {
        title: '新建计划'
      },
      component: () => import( /* webpackChunkName: "login" */ "../views/PlanAdd.vue")
    },
    {
      path: "/tableMachine",
      name: "basetableMachine",
      meta: {
        title: '设备列表'
      },
      component: () => import( /* webpackChunkName: "table" */ "../views/MachineList.vue")
    }, {
      path: "/tableG",
      name: "basetableG",
      meta: {
        title: '分组管理'
      },
      component: () => import( /* webpackChunkName: "table" */ "../views/GroupManager.vue")
    }, {
      path: "/tableUser",
      name: "basetableU",
      meta: {
        title: '账户管理'
      },
      component: () => import( /* webpackChunkName: "table" */ "../views/UserManager.vue")
    }, {
      path: "/charts",
      name: "basecharts",
      meta: {
        title: '图表'
      },
      component: () => import( /* webpackChunkName: "charts" */ "../views/BaseCharts.vue")
    }, {
      path: "/form",
      name: "baseform",
      meta: {
        title: '表单'
      },
      component: () => import( /* webpackChunkName: "form" */ "../views/BaseForm.vue")
    }, {
      path: "/tabs",
      name: "tabs",
      meta: {
        title: 'tab标签'
      },
      component: () => import( /* webpackChunkName: "tabs" */ "../views/Tabs.vue")
    }, {
      path: "/donate",
      name: "donate",
      meta: {
        title: '鼓励作者'
      },
      component: () => import( /* webpackChunkName: "donate" */ "../views/Donate.vue")
    }, {
      path: "/permission",
      name: "permission",
      meta: {
        title: '权限管理',
        permission: true
      },
      component: () => import( /* webpackChunkName: "permission" */ "../views/Permission.vue")
    }, {
      path: "/i18n",
      name: "i18n",
      meta: {
        title: '国际化语言'
      },
      component: () => import( /* webpackChunkName: "i18n" */ "../views/I18n.vue")
    }, {
      path: "/upload",
      name: "upload",
      meta: {
        title: '上传插件'
      },
      component: () => import( /* webpackChunkName: "upload" */ "../views/Upload.vue")
    }, {
      path: "/icon",
      name: "icon",
      meta: {
        title: '自定义图标'
      },
      component: () => import( /* webpackChunkName: "icon" */ "../views/Icon.vue")
    }, {
      path: '/404',
      name: '404',
      meta: {
        title: '找不到页面'
      },
      component: () => import( /* webpackChunkName: "404" */ '../views/404.vue')
    }, {
      path: '/403',
      name: '403',
      meta: {
        title: '没有权限'
      },
      component: () => import( /* webpackChunkName: "403" */ '../views/403.vue')
    }, {
      path: '/user',
      name: 'user',
      meta: {
        title: '个人中心'
      },
      component: () => import( /* webpackChunkName: "user" */ '../views/User.vue')
    }, {
      path: '/editor',
      name: 'editor',
      meta: {
        title: '富文本编辑器'
      },
      component: () => import( /* webpackChunkName: "editor" */ '../views/Editor.vue')
    }]
  }, {
    path: "/login",
    name: "Login",
    meta: {
      title: '登录'
    },
    component: () => import( /* webpackChunkName: "login" */ "../views/Login.vue")
  },
 
];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  document.title = `${to.meta.title} | vue-manage-system`;
  const role = localStorage.getItem('ms_username');
  if (!role && to.path !== '/login') {
    next('/login');
  } else if (to.meta.permission) {
    // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
    role === 'admin' ?
      next() :
      next('/403');
  } else {
    next();
  }
});

export default router;