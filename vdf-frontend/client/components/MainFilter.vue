<template>
  <v-list dense>
    <!-- <v-flex xs12>
      <vdf-misc-filter></vdf-misc-filter>
    </v-flex>
    <v-divider /> -->

    <v-subheader>Sport</v-subheader>
    <vdf-sport-filter />

    <v-subheader>Disciplină</v-subheader>
    <vdf-facet-filter facet-name="discipline" />

    <v-subheader>Etichetă</v-subheader>
    <vdf-facet-filter facet-name="miscellaneous" />

    <v-subheader>Organizator</v-subheader>
    <vdf-facet-filter facet-name="organizer" />
  </v-list>
</template>

<script>
import VdfSportFilter from '~/components/SportFilter'
import VdfMiscFilter from '~/components/MiscFilter'
import VdfFacetFilter from '~/components/FacetFilter'

export default {
  name: 'VdfMainFilter',
  components: {
    VdfFacetFilter,
    VdfMiscFilter,
    VdfSportFilter
  }
}
</script>

<style scoped>
.debug {
  border-style: solid;
}
</style>
