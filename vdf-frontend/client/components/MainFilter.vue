<template>
  <v-list dense>
    <!-- <v-flex xs12>
      <vdf-misc-filter></vdf-misc-filter>
    </v-flex> 

    <v-divider /> -->

    <v-flex>
      <v-subheader>Sport</v-subheader>
      <vdf-sport-filter />
    </v-flex>

    <v-divider />

    <v-flex xs12 pb-5>
      <v-subheader>Disciplină</v-subheader>
      <vdf-facet-filter facet-name="discipline" />
    </v-flex>

    <v-divider />

    <v-flex xs12 pb-5>
      <v-subheader>Etichetă</v-subheader>
      <vdf-facet-filter facet-name="miscellaneous" />
    </v-flex>

    <v-divider />

    <v-flex xs12>
      <v-subheader>Organizator</v-subheader>
      <vdf-facet-filter facet-name="organizer" />
    </v-flex>
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

<style scoped></style>
