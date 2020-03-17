<template>
  <v-btn fab color="darkgray" icon @click="switchDisplayMode">
    <font-awesome-icon :icon="icon" class="fa-lg" />
  </v-btn>
</template>

<script>
export default {
  name: 'VdfDisplayModeSwitch',
  data: () => ({
    icon: 'globe'
  }),
  methods: {
    switchDisplayMode() {
      this.icon = this.icon === 'th' ? 'globe' : 'th'
      this.$eventBus.$emit('toggleDisplayMode')
    }
  }
}
</script>

<style scoped></style>
