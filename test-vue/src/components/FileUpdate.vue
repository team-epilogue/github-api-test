<template>
  <div>
    <h1>File update</h1>
    <label for="repo">레포지토리 :</label>
    <input id="repo" type="text" />
    <!-- <button class="button" @click="fileupload">repository 선택</button> -->

    <label for="dir">폴더 경로 :</label>
    <input id="dir" type="text" />
    <!-- <button class="button" @click="fileupload">폴더 경로</button> -->
    <button class="button" @click="check">조회</button>

    <ul v-if="files">
      <li v-for="(item, index) in files" v-bind:key="index" @click="select(item)">
        <div>{{ item }}</div>
      </li>
      <!-- <button class="button" @click="select">선택</button> -->
    </ul>

    <div v-if="selected">
      <label for="content">내용 :</label>
      <textarea id="content" rows="8" cols="85" v-model="content"></textarea>

      <button class="button" @click="update">업데이트</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      token: "",
      id: "",
      files: [],
      selected: "",
      content: "",
      sha: "",
    };
  },
  created() {
    if (localStorage.getItem("token") && localStorage.getItem("id")) {
      this.token = localStorage.getItem("token");
      this.id = localStorage.getItem("id");
    } else {
      this.$router.push("/");
    }
  },
  methods: {
    check() {
      this.files = [];
      this.selected = "";
      axios({
        url: `https://api.github.com/repos/${this.id}/${document.getElementById("repo").value}/contents/${document.getElementById("dir").value}`,
        method: "get",
      })
        .then(({ data }) => {
          data.forEach((value) => {
            if (value.type === "file") {
              this.files.push(value.name);
            }
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    select(item) {
      this.selected = item;
      axios({
        url: `https://api.github.com/repos/${this.id}/${document.getElementById("repo").value}/contents/${document.getElementById("dir").value}/${this.selected}`,
        method: "get",
      })
        .then(({ data }) => {
          this.content = decodeURIComponent(escape(window.atob(data.content)));
          this.sha = data.sha;
        })
        .catch(() => {
          alert("조회 실패");
        });
    },
    update() {
      let content = window.btoa(unescape(encodeURIComponent(this.content)));
      axios({
        url: `https://api.github.com/repos/${this.id}/${document.getElementById("repo").value}/contents/${document.getElementById("dir").value}/${this.selected}`,
        method: "put",
        headers: {
          Authorization: `Bearer ${this.token}`,
        },
        data: {
          message: "Modify: vue test",
          content: content,
          sha: this.sha,
        },
      })
        .then(() => {
          alert("업데이트 성공");
        })
        .catch(() => {
          alert("업데이트 실패");
        });
    },
  },
};
</script>

<style scoped>
ul {
  list-style-type: none;
  background-color: #ccc;
  width: 180px;
  padding: 0;
  margin: auto;
  margin-top: 10px;
}

li div {
  text-decoration: none;
  display: block;
  color: #000;
  padding: 8px 15px 8px 15px;
  font-weight: bold;
  border-bottom: 1px solid #fff;
}

li div:hover {
  background-color: tomato;
  color: #fff;
}
</style>
