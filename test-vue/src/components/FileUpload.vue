<template>
  <div>
    <h1>File Upload</h1>
    <label for="repo">레포지토리 :</label>
    <input id="repo" type="text" />
    <!-- <button class="button" @click="fileupload">repository 선택</button> -->

    <label for="dir">폴더 경로 :</label>
    <input id="dir" type="text" />
    <!-- <button class="button" @click="fileupload">폴더 경로</button> -->

    <label for="content">내용 :</label>
    <textarea id="content"></textarea>

    <button class="button" @click="fileupload">업로드</button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      token: "",
      id: "",
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
    fileupload() {
      const time = Date.now();
      const timestamp = Math.floor(time / 1000);
      let content = window.btoa(unescape(encodeURIComponent(document.getElementById("content").value)));

      axios({
        url: `https://api.github.com/repos/${this.id}/${document.getElementById("repo").value}/contents/${document.getElementById("dir").value}/${timestamp}.md`,
        method: "put",
        headers: {
          Authorization: `Bearer ${this.token}`,
        },
        data: {
          message: "Add: vue test",
          content: content,
        },
      })
        .then(() => {
          alert("업로드 성공");
        })
        .catch(() => {
          alert("업로드 실패");
        });
    },
  },
};
</script>
