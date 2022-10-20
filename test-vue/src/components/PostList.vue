<template>
  <div>
    <v-row v-for="item in postList" :key="item">
      <v-col>{{ item }}</v-col>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import { Base64 } from "js-base64";

export default {
  name: "App",
  data() {
    return {
      token: "",
      postList: [],
    };
  },
  created() {},
  mounted() {
    if (localStorage.getItem("token")) {
      this.token = localStorage.getItem("token");
    }
    this.getList();
  },
  methods: {
    getList() {
      axios({
        method: "get",
        //테스트라 url이라 내거로 고정임 / huni-hun < git계정 / testRepo < repo 이름 / contentes < api 양식 / post < 탐색 경로
        url: `https://api.github.com/repos/huni-hun/testRepo/contents/post`,
        headers: {
          accept: "application/vnd.hithun+json",
          Authorization: `Bearer ` + this.token,
        },
      }).then((res) => {
        console.log(res);
        for (let index = 0; index < res.data.length; index++) {
          const path = res.data[index].path;
          this.setItem(path, index);
        }
      });
    },

    setItem(path, i) {
      axios({
        method: "get",
        //테스트라 url이라 내거로 고정임 / huni-hun < git계정 / testRepo < repo 이름 / contentes < api 양식
        url: `https://api.github.com/repos/huni-hun/testRepo/contents/` + path,
        headers: {
          accept: "application/vnd.hithun+json",
          Authorization: `Bearer ` + this.token,
        },
      }).then((res) => {
        this.postList[i] = Base64.decode(res.data.content);
        console.log(this.postList[i]);
      });
    },
  },
};
</script>
