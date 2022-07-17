<template>
  <div>
    <overview />
    <gcCalendar />
  </div>
</template>

<script>
import gcCalendar from "@/views/homeView/_gcCalendar.vue";
import overview from "@/views/homeView/_overview.vue";

export default {
  components: {
    overview,
    gcCalendar,
  },
};
</script>
