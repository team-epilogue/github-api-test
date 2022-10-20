import { createRouter, createWebHistory } from "vue-router";
import Main from "../components/Main.vue";
import GithubLogin from "../components/GithubLogin.vue";
import FileUpload from "../components/FileUpload.vue";
import PostList from "../components/PostList.vue";
import RepoCreate from "../components/RepoCreate.vue";

const routes = [
  { path: "/", name: "main", component: Main },
  { path: "/login", name: "login", component: GithubLogin },
  { path: "/fileupload", name: "fileupload", component: FileUpload },
  { path: "/list", name: "list", component: PostList },
  { path: "/repocreate", name: "repocreate", component: RepoCreate },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
