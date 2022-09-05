import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
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
