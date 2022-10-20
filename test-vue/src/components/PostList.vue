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
      postList: [],
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    getList() {
      axios({
        method: "get",
        //테스트라 url이 내거로 고정임
        url: `https://api.github.com/repos/huni-hun/testRepo/contents/post`,
        headers: {
          accept: "application/vnd.hithun+json",
          //테스트라 토큰이 내거로 고정임
          Authorization: `Bearer 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`,
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
        //테스트라 url이 내거로 고정임
        url: `https://api.github.com/repos/huni-hun/testRepo/contents/` + path,
        headers: {
          accept: "application/vnd.hithun+json",
          //테스트라 토큰이 내거로 고정임
          Authorization: `Bearer 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`,
        },
      }).then((res) => {
        this.postList[i] = Base64.decode(res.data.content);
        console.log(this.postList[i]);
      });
    },
  },
};
</script>
