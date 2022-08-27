import axios from "axios";

export default {
  methods: {
    async post(url, request) {
      axios.defaults.baseURL = process.env.VUE_APP_API_ENDPOINT;

      return await axios
        .post("/api" + url, request, {
          headers: {
            "X-AUTH-TOKEN": this.$store.getters.getToken,
          },
        })
        .then((res) => {
          return res;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
