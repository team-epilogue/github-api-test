<template>
  <div>
    <h1>파일 생성</h1>
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
          <label for="dir">폴더 경로: </label>
          <input id="dir" type="text" style="width: 300px" />
          <!-- <button class="button" @click="fileupload">폴더 경로</button> -->
          <br />
          <br />
          <label for="content">내용</label>
          <br />
          <textarea id="content" rows="40" cols="70" v-model="content"></textarea>
          <br />
          <button class="button" @click="fileupload">업로드</button>
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
      content: "",
      selectedRepo: "",
      repoList: [],
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
    fileupload() {
      const time = Date.now();
      const timestamp = Math.floor(time / 1000);
      let content = window.btoa(unescape(encodeURIComponent(this.content)));

      axios({
        url: `https://api.github.com/repos/${this.id}/${this.selectedRepo}/contents/${document.getElementById("dir").value}/${timestamp}.md`,
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
          this.$router.push("/");
        })
        .catch(() => {
          alert("업로드 실패");
        });
    },
  },
};
</script>

<style scoped>
ul {
  list-style-type: none;
  background-color: #ccc;
  width: 500px;
  padding: 0;
  margin: auto;
  margin-top: 10px;
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
