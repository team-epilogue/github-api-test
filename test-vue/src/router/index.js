import { createRouter, createWebHistory } from "vue-router";
import Main from "../components/Main.vue";
import GithubLogin from "../components/GithubLogin.vue";
import PostList from "../components/PostList.vue";

const routes = [
  { path: "/", name: "main", component: Main },
  { path: "/login", name: "login", component: GithubLogin },
  { path: "/list", name: "list", component: PostList },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
