<template>
  <v-list dense>
    <!-- <v-flex xs12>
      <vdf-misc-filter></vdf-misc-filter>
    </v-flex>
    <v-divider /> -->

    <v-subheader>Sport</v-subheader>
    <vdf-sport-filter />

    <v-subheader>Disciplină</v-subheader>
    <ais-refinement-list
      attribute="discipline"
      :sort-by="['count:desc', 'name:asc']"
    />

    <v-subheader>Etichetă</v-subheader>
    <ais-refinement-list
      attribute="miscellaneous"
      :sort-by="['count:desc', 'name:asc']"
    />

    <v-subheader>Organizator</v-subheader>
    <ais-refinement-list
      attribute="organizer"
      :sort-by="['count:desc', 'name:asc']"
    />
  </v-list>
</template>

<script>
import VdfSportFilter from '~/components/SportFilter'
import VdfFacetFilter from '~/components/FacetFilter'
import AisRefinementList from 'vue-instantsearch'

export default {
  name: 'VdfMainFilter',
  components: {
    AisRefinementList,
    VdfFacetFilter,
    VdfSportFilter,
  },
}
</script>

<style scoped>
.debug {
  border-style: solid;
}
</style>
