<template>
  <v-container grid-list-md>
    <v-layout row wrap xs12>
      <v-flex
        v-for="vdfEvent in results"
        v-if="showGrid"
        :key="vdfEvent.id"
        md4
        sm6
        xs12
      >
        <vdf-event-card :vdf-event="vdfEvent"></vdf-event-card>
      </v-flex>
      <v-flex v-if="!showGrid" xs12>
        <vdf-map :vdf-events="results"></vdf-map>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { Component } from 'vue-instantsearch'
import EventCard from './EventCard'
import VdfMap from './map/VdfMap'

export default {
  name: 'VdfResults',
  components: {
    'vdf-event-card': EventCard,
    'vdf-map': VdfMap
  },
  mixins: [Component],
  data: () => ({
    showGrid: true
  }),
  computed: {
    results: function results() {
      return this.searchStore.results
    }
  },
  created: function created() {
    this.$eventBus.$on('toggleDisplayMode', () => {
      this.showGrid = !this.showGrid
    })
  },
  mounted: function() {
    this.searchStore.start()
  }
}
</script>

<style scoped></style>
