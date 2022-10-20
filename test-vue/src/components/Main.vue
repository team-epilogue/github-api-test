<template>
  <div v-if="token">
    <button class="button" @click="logout">logout</button>
    <div class="token">{{ token }}</div>
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
    };
  },
  created() {
    if (localStorage.getItem("token")) {
      this.token = localStorage.getItem("token");
    }
  },
  methods: {
    logout() {
      localStorage.removeItem("token");
      this.token = "";
    },
    redirect: function (url) {
      window.location.href = url;
    },
    login: function () {
      this.redirect(`https://github.com/login/oauth/authorize?client_id=${process.env.VUE_APP_GITHUB_CLIENT_ID}&scope=repo`);
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
}
</style>
