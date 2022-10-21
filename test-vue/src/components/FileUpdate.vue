<template>
  <div>
    <h1>파일 수정</h1>
    <div style="display: flex">
      <div style="flex: 1; border-right: dashed 2px #979797">
        <div v-if="repoList">
          <label for="repo">레포지토리</label>
          <ul id="repo">
            <li v-for="(item, index) in repoList" v-bind:key="index" @click="selectRepo(item)">
              <div>{{ item }}</div>
            </li>
          </ul>
        </div>
      </div>
      <div style="flex: 1">
        <div v-if="selectedRepo">
          <br />
          <label for="dir">폴더 경로 : </label>
          <input id="dir" type="text" v-model="dir" style="width: 300px" />
          <button @click="check" style="margin-left: 5px">조회</button>

          <ul v-if="files" id="filedir">
            <li v-for="(item, index) in files" v-bind:key="index" @click="select(item)">
              <div>{{ item }}</div>
            </li>
          </ul>

          <div v-if="selected" style="margin-top: 15px">
            <label for="content">내용</label>
            <br />
            <textarea id="content" rows="40" cols="70" v-model="content"></textarea>
            <br />
            <button class="button" @click="update">업데이트</button>
          </div>
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
      dir: "",
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
  watch: {
    dir: function () {
      this.selected = "";
      this.files = [];
    },
  },
  methods: {
    selectRepo(item) {
      this.selected = "";
      this.files = [];
      this.dir = "";
      this.selectedRepo = item;
    },
    check() {
      this.files = [];
      this.selected = "";
      axios({
        url: `https://api.github.com/repos/${this.id}/${this.selectedRepo}/contents/${this.dir}`,
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
        url: `https://api.github.com/repos/${this.id}/${this.selectedRepo}/contents/${this.dir}/${this.selected}`,
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
        url: `https://api.github.com/repos/${this.id}/${this.selectedRepo}/contents/${this.dir}/${this.selected}`,
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
  width: 500px;
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

#repo li div:hover {
  background-color: #c7df83;
  color: #fff;
}

#filedir li div:hover {
  background-color: tomato;
  color: #fff;
}

.button {
  width: 100px;
  height: 50px;
  font-weight: bold;
  font-size: 20px;
  background: #6d7e85;
  color: white;
  border-radius: 10px;
  margin-top: 10px;
  align-items: center;
  justify-content: center;
}
.button:hover {
  cursor: pointer;
  background-color: #9ab4bf;
}
</style>
