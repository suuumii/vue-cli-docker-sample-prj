<template>
  <Loading
    :active.sync="isLoading"
    :can-cancel="true"
    :is-full-page="true"
  ></Loading>
</template>

<script>
import Loading from "vue-loading-overlay";
import "vue-loading-overlay/dist/vue-loading.css";

export default {
  components: {
    Loading,
  },
  data() {
    return {
      hoge: true,
    };
  },
  computed: {
    isLoading: {
      get() {
        return this.$store.getters.getIsLoading;
      },
      set(val) {
        this.$store.commit("setIsLoading", val);
      },
    },
  },
};
</script>
<style scoped></style>
