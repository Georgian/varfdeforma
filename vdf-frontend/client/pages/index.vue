<template>
  <v-content>
    <ais-instant-search-ssr index-name="vdf">
      <!-- <vdf-nav-drawer /> -->
      <vdf-header show-drawer-icon />
      <v-container grid-list-xl>
        <v-layout row wrap>
          <v-flex lg2 pl-0 hidden-md-and-down>
            <v-list dense>
              <v-subheader>Sport</v-subheader>

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
          </v-flex>
          <v-flex lg10>
            <ais-hits>
              <template slot-scope="{ items }">
                <vdf-hits :results="items" />
              </template>
            </ais-hits>
          </v-flex>
        </v-layout>
      </v-container>
      <!-- <vdf-footer />-->
    </ais-instant-search-ssr>
  </v-content>
</template>

<script>
import {
  AisHits,
  AisRefinementList,
  AisInstantSearchSsr,
} from 'vue-instantsearch'
import VdfHits from '../components/Hits'
import VdfMainFilter from '../components/MainFilter'
import VdfHeader from '~/components/Header'
import VdfFooter from '~/components/Footer'
import VdfNavDrawer from '~/components/NavDrawer'

export default {
  components: {
    AisInstantSearchSsr,
    AisRefinementList,
    VdfHeader,
    VdfFooter,
    VdfNavDrawer,
    VdfMainFilter,
    AisHits,
    VdfHits,
    // VdfMiscFilter,
    // VdfSportFilter,
  },
  asyncData({ app }) {
    const instantsearch = app.$instantsearch
    return instantsearch
      .findResultsState({
        // hitsPerPage: 1,
        facets: ['miscellaneous', 'discipline', 'organizer', 'sport'],
      })
      .then(() => ({
        instantSearchState: instantsearch.getState(),
      }))
  },
  data: () => ({
    drawer: null,
    instantSearchState: null,
  }),
  beforeMount() {
    // Nuxt will merge `asyncData` and `data` on the client
    this.$instantsearch.hydrate(this.instantSearchState)
  },
  methods: {
    getMarkerLocation: function (locationCoordinates) {
      let split = locationCoordinates.split(',')
      return { lat: parseFloat(split[0]), lng: parseFloat(split[1]) }
    },
  },
  provide() {
    return {
      $_ais: this.$instantsearch,
    }
  },
  // head() {
  //   return {
  //     link: [
  //       {
  //         rel: 'stylesheet',
  //         href:
  //           'https://unpkg.com/instantsearch.css@7.1.0/themes/algolia-min.css',
  //       },
  //     ],
  //   }
  // },
}
</script>

<style></style>
