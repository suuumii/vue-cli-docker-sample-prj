<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">Latest Release</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
    <v-footer app padless>
      <v-row justify="center" no-gutters>
        <v-btn
          v-for="(item, idx) in footerIconList"
          :key="idx"
          class="mx-2"
          fab
          :color="item.color"
          :text="isSelectedIcon(item.id)"
          @click="select(item.id)"
        >
          <span class="font-weight-bold">{{ item.name }}</span>
        </v-btn>
      </v-row>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: "App",

  data() {
    return {
      footerIconList: [
        {
          id: 1,
          name: "可燃",
          color: "#DD72A1",
        },
        {
          id: 2,
          name: "不燃",
          color: "#6EB92B",
        },
        {
          id: 3,
          name: "プラ",
          color: "#F39700",
        },
        {
          id: 4,
          name: "資源",
          color: "#00AFEC",
        },
      ],
      selectItemId: 1, // 選択済みアイコン
    };
  },
  methods: {
    // 選択済みかどうか判定
    isSelectedIcon(iconId) {
      return iconId !== this.selectItemId;
    },
    // 選択済みアイコン情報を更新
    select(selectId) {
      this.selectItemId = selectId;
    },
  },
};
</script>
<style scoped></style>
