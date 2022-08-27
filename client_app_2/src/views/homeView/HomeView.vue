<template>
  <div>
    <overview />
    <router-view />
  </div>
</template>

<script>
import overview from "@/views/homeView/_overview.vue";

export default {
  components: {
    overview,
  },
};
</script>
