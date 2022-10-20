<template>
  <div>
    <h1>레포지토리 생성</h1>
    <label for="repo">레포지토리 이름:</label>
    <input id="repo" type="text" />
    <button class="button" @click="repocreate">생성</button>
    <div><button class="button" @click="loadrepo">레포지토리 리스트 불러오기</button></div>
    <div v-if="repoList">
      <div v-for="item in repoList" :key="item">
        <v-col>{{ item }}</v-col>
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
      repoList: [],
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
    repocreate() {
      axios({
        url: `https://api.github.com/user/repos`,
        method: "post",
        headers: {
          Authorization: `Bearer ${this.token}`,
        },
        data: {
          name: document.getElementById("repo").value,
        },
      })
        .then(() => {
          alert("레포 생성 성공");
        })
        .catch(() => {
          alert("레포 생성 실패");
        });
    },
    loadrepo() {
      axios({
        url: `https://api.github.com/users/${this.id}/repos`,
        method: "get",
      })
        .then((res) => {
          res.data.forEach((repo, index) => {
            this.repoList[index] = repo.name;
          });
          alert("레포 리스트 로드 성공");
        })
        .catch(() => {
          alert("레포 리스트 로드 실패");
        });
    },
  },
};
</script>
