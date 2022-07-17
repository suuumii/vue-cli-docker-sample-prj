<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <v-img
          alt="gcIcon"
          class="shrink mr-2"
          contain
          :src="require('@/assets/gcIcon.svg')"
          transition="scale-transition"
          width="40"
        />
        <span>わが家のゴミ分別カレンダー</span>
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://www.google.com/search?q=ゴミ分別カレンダー"
        target="_blank"
        text
      >
        <span class="mr-2">「ゴミ分別カレンダー」で検索</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic&display=swap");
#app {
  font-family: "Zen Maru Gothic", sans-serif;
}
</style>
