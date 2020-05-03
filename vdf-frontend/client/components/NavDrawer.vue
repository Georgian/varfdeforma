<template>
  <v-navigation-drawer
    v-model="showDrawer"
    fixed
    clipped
    class="grey lighten-4"
    app
    disable-resize-watcher
    disable-route-watcher
  >
    // TODO for mobile
    <!--    <vdf-main-filter />-->
  </v-navigation-drawer>
</template>

<script>
export default {
  name: 'VdfNavDrawer',
  components: {
  },
  data: () => ({
    showDrawer: false,
  }),
  created() {
    // TODO
    // this.$eventBus.$on('toggleDrawer', () => {
    //   this.showDrawer = !this.showDrawer
    // })
  },
}
</script>

<style scoped></style>
