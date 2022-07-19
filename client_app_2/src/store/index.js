import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLoading: false,
  },
  getters: {
    getIsLoading: (state) => state.isLoading,
  },
  mutations: {
    setIsLoading(state, value) {
      state.isLoading = value;
    },
  },
  actions: {},
  modules: {},
});
