import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLoading: false,
    token: "",
  },
  getters: {
    getIsLoading: (state) => state.isLoading,
    getToken: (state) => state.token,
  },
  mutations: {
    setIsLoading(state, value) {
      state.isLoading = value;
    },
    setToken(state, value) {
      state.token = value;
    },
  },
  actions: {},
  modules: {},
});
