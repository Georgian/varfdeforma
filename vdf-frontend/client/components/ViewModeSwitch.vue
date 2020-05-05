<template>
  <v-btn fab color="darkgray" icon @click="setNextViewMode">
    <font-awesome-icon :icon="getIcon" class="fa-lg" />
  </v-btn>
</template>

<script>
export default {
  name: 'ViewModeSwitch',
  computed: {
    getIcon() {
      const nextViewMode = this.$store.getters['modules/ux/getNextViewMode']
      // TODO lol
      switch (nextViewMode) {
        case 'cards':
          return 'th'
        case 'map':
          return 'globe'
        default:
          return ''
      }
    },
  },
  methods: {
    setNextViewMode() {
      this.$store.dispatch('modules/ux/setNextViewMode')
    },
  },
}
</script>

<style scoped></style>
