<template>
  <div v-if="token && id">
    <button class="button" @click="logout">logout</button>
    <div class="token">{{ token }}</div>
    <div class="userid">{{ id }}</div>

    <button class="button" @click="fileupload">file upload</button>
    <button class="button" @click="moveToList">리스트 이동</button>
  </div>
  <div v-else>
    <button class="button" @click="login">
      {{ "Login with github" }}
    </button>
  </div>
</template>

<script>
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
    }
  },
  methods: {
    logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("id");
      this.token = "";
      this.id = "";
    },
    redirect(url) {
      window.location.href = url;
    },
    login() {
      this.redirect(`https://github.com/login/oauth/authorize?client_id=${process.env.VUE_APP_GITHUB_CLIENT_ID}&scope=repo`);
    moveToList() {
      this.$router.push({
        path: `/list`,
      });
    },
    fileupload() {
      this.$router.push("/fileupload");
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
}
.token {
  font-size: 20px;
  text-align: left;
  margin-bottom: 5px;
}
.userid {
  font-size: 20px;
  text-align: left;
  margin-bottom: 30px;
}
</style>
