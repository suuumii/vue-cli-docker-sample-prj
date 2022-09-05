import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/homeView/HomeView.vue";
import LoginForm from "@/views/homeView/LoginForm";
import gcCalendar from "@/views/homeView/_gcCalendar.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "login",
    redirect: "/login",
  },
  {
    path: "",
    name: "home",
    component: HomeView,
    children: [
      {
        path: "login",
        component: LoginForm,
      },
      {
        path: "calendar",
        component: gcCalendar,
      },
    ],
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "@/views/about/AboutView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
