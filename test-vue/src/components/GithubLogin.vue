<template>
  <div>
    <h1>GITHUB LOGIN</h1>
  </div>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    redirect: function (url) {
      window.location.href = url;
    },
  },
  created() {
    const redirect = this.redirect;
    console.log(this.$route.query.code);
    axios.get("http://localhost:3000/github/login?code=" + this.$route.query.code).then(function (res) {
      if (!res.data) {
        alert("토큰받기 실패");
      } else {
        localStorage.setItem("token", res.data);
        alert("토큰받기 성공");
      }
      redirect("/");
    });
  },
};
</script>
