<template>
  <v-app>
    <appHeader />
    <loadingIcon />
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import appHeader from "@/components/AppHeader.vue";
import loadingIcon from "@/components/LoadingIcon.vue";

export default {
  name: "App",
  components: {
    appHeader,
    loadingIcon,
  },
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap");
#app {
  font-family: "Zen Maru Gothic", sans-serif;
}
</style>
