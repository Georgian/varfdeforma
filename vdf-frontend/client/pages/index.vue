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
              <ais-menu attribute="sport" :sort-by="['count:desc', 'name:asc']">
                <v-radio-group
                  slot-scope="{ items, refine }"
                  :v-model="radioValue"
                  :mandatory="true"
                  hide-details
                >
                  <v-radio label="Toate" @change="refine()" />
                  <v-radio
                    v-for="item in items"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    @change="refine(item.value)"
                  />
                </v-radio-group>
              </ais-menu>

              <v-subheader>Disciplină</v-subheader>
              <vdf-refinement-list attribute="discipline" />

              <v-subheader>Etichetă</v-subheader>
              <vdf-refinement-list attribute="miscellaneous" />

              <v-subheader>Organizator</v-subheader>
              <vdf-refinement-list attribute="organizer" />
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
import { AisHits, AisInstantSearchSsr, AisMenu } from 'vue-instantsearch'
import VdfHits from '../components/Hits'
import VdfMainFilter from '../components/MainFilter'
import VdfHeader from '~/components/Header'
import VdfFooter from '~/components/Footer'
import VdfNavDrawer from '~/components/NavDrawer'
import VdfRefinementList from '../components/search/VdfRefinementList'

export default {
  components: {
    VdfRefinementList,
    AisInstantSearchSsr,
    VdfHeader,
    AisMenu,
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
        facets: ['miscellaneous', 'discipline', 'organizer'],
        hierarchicalFacets: [
          {
            name: 'sport',
            attributes: ['MTB', 'Sosea', 'Ciclocross'],
          },
        ],
      })
      .then(() => ({
        instantSearchState: instantsearch.getState(),
      }))
  },
  data: () => ({
    drawer: null,
    instantSearchState: null,
    radioValue: 'Toate',
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
