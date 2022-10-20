import { createRouter, createWebHistory } from "vue-router";
import Main from "../components/Main.vue";
import GithubLogin from "../components/GithubLogin.vue";

const routes = [
  { path: "/", name: "main", component: Main },
  { path: "/login", name: "login", component: GithubLogin },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
