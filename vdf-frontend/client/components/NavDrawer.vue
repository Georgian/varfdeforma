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
    <vdf-main-filter/>
  </v-navigation-drawer>
</template>

<script>
  import VdfMainFilter from '~/components/MainFilter'

  export default {
    name: 'vdf-nav-drawer',
    data: () => ({
      showDrawer: false
    }),
    components: {
      VdfMainFilter
    },
    created() {
      this.$eventBus.$on('toggleDrawer', () => {
        this.showDrawer = !this.showDrawer
      })
    }
  }
</script>

<style scoped>

</style>
