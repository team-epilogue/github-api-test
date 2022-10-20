<template>
  <div>
    <h1>GITHUB LOGIN</h1>
  </div>
</template>

<script>
import axios from "axios";

export default {
  created() {
    axios({
      url: `http://localhost:3000/github/login?code=${this.$route.query.code}`,
      method: "get",
    })
      .then((res) => {
        if (!res.data) {
          alert("토큰받기 실패");
          this.$router.push("/");
        } else {
          axios({
            url: `https://api.github.com/user`,
            method: "get",
            headers: {
              Authorization: `Bearer ${res.data}`,
            },
          })
            .then((response) => {
              localStorage.setItem("token", res.data);
              localStorage.setItem("id", response.data.login);
              alert("토큰받기 성공");
              this.$router.push("/");
            })
            .catch(() => {
              alert("토큰받기 실패");
              this.$router.push("/");
            });
        }
      })
      .catch(() => {
        alert("토큰받기 실패");
        this.$router.push("/");
      });
  },
};
</script>
