<template>
  <div>
    <v-card width="400px" class="mx-auto my-5">
      <v-card-title>
        <h3 class="ma-auto">ログイン</h3>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field
            v-model="username"
            prepend-icon="mdi-account-circle"
            label="Username"
          />
          <v-text-field
            v-model="password"
            :type="showPassword ? 'text' : 'password'"
            @click:append="showPassword = !showPassword"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            prepend-icon="mdi-lock"
            label="Password"
          />
          <v-card-actions>
            <v-btn class="info ma-auto" @click="pushLoginBtn">ログイン</v-btn>
          </v-card-actions>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      showPassword: false,
    };
  },
  methods: {
    pushLoginBtn() {
      this.login();
    },
    async login() {
      // Loading
      this.$store.commit("setIsLoading", true);

      let parameter = {
        username: this.username,
        password: this.password,
      };

      const res = await this.post("/login", parameter);
      if (res && res.status === 200) {
        // login success
        this.$store.commit("setToken", res.headers["x-auth-token"]);
        this.$router.push("/calendar");
      } else {
        alert("ログイン失敗");
      }
      // Loading
      this.$store.commit("setIsLoading", false);
    },
  },
};
</script>
