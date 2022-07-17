import axios from "axios";

export default {
  methods: {
    async post(url, request) {
      axios.defaults.baseURL = process.env.VUE_APP_API_ENDPOINT;

      await axios
        .post("/api" + url, request)
        .then((res) => {
          return res;
        })
        .catch((error) => {
          window.alert(error);
          console.log(error);
        });
    },
  },
};
