<template>
  <div>
    <h1>File update</h1>
    <div v-if="repoList">
      <label for="repo">레포지토리 :</label>
      <ul id="repo">
        <li v-for="(item, index) in repoList" v-bind:key="index" @click="selectRepo(item)">
          <div>{{ item }}</div>
        </li>
        <!-- <button class="button" @click="select">선택</button> -->
      </ul>
      <!-- <input id="repo" type="text" /> -->
      <!-- <button class="button" @click="fileupload">repository 선택</button> -->
      <div v-if="selectedRepo">
        <!-- <button class="button" @click="fileupload">repository 선택</button> -->
        <br />
        <label for="dir">폴더 경로 :</label>
        <input id="dir" type="text" />
        <!-- <button class="button" @click="fileupload">폴더 경로</button> -->
        <button class="button" @click="check">조회</button>

        <ul v-if="files" id="filedir">
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
      selected: "",
      files: [],
      selectedRepo: "",
      repoList: [],
      content: "",
      sha: "",
    };
  },
  created() {
    if (localStorage.getItem("token") && localStorage.getItem("id")) {
      this.token = localStorage.getItem("token");
      this.id = localStorage.getItem("id");
      axios({
        url: `https://api.github.com/users/${this.id}/repos`,
        method: "get",
      })
        .then(({ data }) => {
          data.forEach((value) => {
            this.repoList.push(value.name);
          });
        })
        .catch(() => {
          alert("레포 리스트 로드 실패");
          this.$router.push("/");
        });
    } else {
      this.$router.push("/");
    }
  },
  methods: {
    selectRepo(item) {
      this.selectedRepo = item;
    },
    check() {
      this.files = [];
      this.selected = "";
      axios({
        url: `https://api.github.com/repos/${this.id}/${this.selectedRepo}/contents/${document.getElementById("dir").value}`,
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
        url: `https://api.github.com/repos/${this.id}/${this.selectedRepo}/contents/${document.getElementById("dir").value}/${this.selected}`,
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
        url: `https://api.github.com/repos/${this.id}/${this.selectedRepo}/contents/${document.getElementById("dir").value}/${this.selected}`,
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
          this.$router.push("/");
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
  padding: 0;
  margin: auto;
  margin-top: 10px;
}

#repo {
  width: 500px;
}

#repo li div {
  text-decoration: none;
  display: block;
  color: #000;
  padding: 8px 15px 8px 15px;
  font-weight: bold;
  border-bottom: 1px solid #fff;
}

#repo li div:hover {
  background-color: #c7df83;
  color: #fff;
}

#filedir {
  width: 200px;
}

#filedir li div {
  text-decoration: none;
  display: block;
  color: #000;
  padding: 8px 15px 8px 15px;
  font-weight: bold;
  border-bottom: 1px solid #fff;
}

#filedir li div:hover {
  background-color: tomato;
  color: #fff;
}
</style>
