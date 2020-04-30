<template>
  <v-content>
    <v-container grid-list-xl>
      <v-layout row wrap>
        <v-flex lg2 pl-0 hidden-md-and-down>
          <vdf-main-filter />
        </v-flex>
        <v-flex lg10>
          <vdf-results></vdf-results>
        </v-flex>
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
import VdfSportFilter from '~/components/SportFilter'
import VdfMiscFilter from '~/components/MiscFilter'
import VdfResults from '~/components/Results'
import VdfMainFilter from '../components/MainFilter'

export default {
  components: {
    VdfMainFilter,
    VdfResults,
    VdfMiscFilter,
    VdfSportFilter
  },
  data: () => ({
    drawer: null
  }),
  methods: {
    getMarkerLocation: function(locationCoordinates) {
      let split = locationCoordinates.split(',')
      return { lat: parseFloat(split[0]), lng: parseFloat(split[1]) }
    }
  }
}
</script>

<style></style>
