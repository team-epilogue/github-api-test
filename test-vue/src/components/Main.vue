<template>
  <div style="display: flex">
    <div style="flex: 1; display: flex; flex-direction: column; align-items: center">
      <div v-if="token && id" style="display: flex; flex-direction: column; align-items: center">
        <button class="button" @click="logout">로그아웃</button>
        <div style="margin: 10px; padding: 40px; background: #e1ebdd; box-shadow: 0px 10px 10px rgba(0, 0, 0, 0.25); border-radius: 50px">
          <div style="font-size: 20px">프로필</div>
          <img :src="image" alt="프로필 이미지" style="width: 150px; height: 150px" />
          <div class="token">token: {{ token }}</div>
          <div class="userid">id: {{ id }}</div>
        </div>
      </div>
      <div v-else>
        <button class="button" @click="login" style="display: flex; flex-direction: column; align-items: center">로그인</button>
      </div>
    </div>
    <div style="flex: 1">
      <div style="display: flex; flex-direction: column; align-items: center" v-if="token && id">
        <button class="button" @click="fileupload">파일 생성</button>
        <button class="button" @click="fileupdate">파일 수정</button>
        <button class="button" @click="moveToList">리스트 이동</button>
        <button class="button" @click="repocreate">레포 만들기</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      token: "",
      id: "",
      image: "",
    };
  },
  created() {
    if (localStorage.getItem("token") && localStorage.getItem("id")) {
      this.token = localStorage.getItem("token");
      this.id = localStorage.getItem("id");
      this.image = localStorage.getItem("image");
    }
  },
  methods: {
    logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("id");
      localStorage.removeItem("image");
      this.token = "";
      this.id = "";
      this.image = "";
    },
    redirect(url) {
      window.location.href = url;
    },
    login() {
      this.redirect(`https://github.com/login/oauth/authorize?client_id=${process.env.VUE_APP_GITHUB_CLIENT_ID}&scope=repo`);
      // let win = window.open(`https://github.com/login/oauth/authorize?client_id=${process.env.VUE_APP_GITHUB_CLIENT_ID}&scope=repo`, "", "_blank");
      // setTimeout(function () {
      //   win.closed();
      // }, 3000);
    },
    moveToList() {
      this.$router.push({
        path: `/list`,
      });
    },
    fileupload() {
      this.$router.push("/fileupload");
    },
    fileupdate() {
      this.$router.push("/fileupdate");
    },
    repocreate() {
      this.$router.push("/repocreate");
    },
  },
};
</script>

<style scoped>
.button {
  width: 400px;
  height: 50px;
  font-weight: bold;
  font-size: 30px;
  background: #6d7e85;
  color: white;
  border-radius: 10px;
  margin-top: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.button:hover {
  cursor: pointer;
  background-color: #9ab4bf;
}
.token {
  font-size: 15px;
  text-align: left;
  margin-bottom: 5px;
}
.userid {
  font-size: 15px;
  text-align: left;
}
</style>
