module.exports = {
  env: {
    node: true,
  },
  extends: [
    "eslint:recommended",
    "plugin:prettier/recommended",
    "plugin:vue/strongly-recommended",
    "prettier",
  ],
  parserOptions: {
    parser: "babel-eslint",
  },
  plugins: ["prettier", "vue", "vuetify"],
  rules: {
    "prettier/prettier": [
      "error",
      {
        tabWidth: 2,
        semi: true,
        singleQuote: false,
        bracketSpacing: true,
      },
    ],
    "no-plusplus": "off",
    "func-names": "off",
    "no-console": "warn",
    "import/no-unresolved": "off",
    "vue/require-prop-types": "off",
    "vue/require-default-prop": "off",
    "vuetify/no-deprecated-classes": "error",
  },
};
