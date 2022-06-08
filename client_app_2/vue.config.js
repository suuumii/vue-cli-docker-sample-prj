const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  configureWebpack: {
    watch: true,
    watchOptions: {
      ignored: /node_modules/,
      aggregateTimeout: 200,
      poll: 1000,
    },
  },
});
