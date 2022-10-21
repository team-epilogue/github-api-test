import { createRouter, createWebHistory } from "vue-router";
import Main from "../components/Main.vue";
import GithubLogin from "../components/GithubLogin.vue";
import FileUpload from "../components/FileUpload.vue";
import FileUpdate from "../components/FileUpdate.vue";
import PostList from "../components/PostList.vue";

const routes = [
  { path: "/", name: "main", component: Main },
  { path: "/login", name: "login", component: GithubLogin },
  { path: "/fileupload", name: "fileupload", component: FileUpload },
  { path: "/fileupdate", name: "fileupdate", component: FileUpdate },
  { path: "/list", name: "list", component: PostList },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
