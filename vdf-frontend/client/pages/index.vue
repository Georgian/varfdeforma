<template>
  <v-app id="vdf">
    <ais-instant-search-ssr index-name="vdf">
      <!-- <vdf-nav-drawer /> -->
      <vdf-header show-drawer-icon />
      <v-content>
        <v-container grid-list-xl>
          <v-layout row wrap>
            <v-flex lg2 pl-0 hidden-md-and-down>
              <v-container lg2 pl-0 hidden-md-and-down>
                <v-row>
                  <v-col class="pa-0">
                    <v-flex pa-0 pb-2>
                      <vdf-sport-menu title="Sport" attribute="sport" />
                    </v-flex>
                    <v-flex pa-0 pb-2>
                      <vdf-refinement-list
                        title="Disciplină"
                        attribute="discipline"
                      />
                    </v-flex>
                    <v-flex pa-0 pb-2>
                      <vdf-refinement-list
                        title="Etichetă"
                        attribute="miscellaneous"
                      />
                    </v-flex>
                    <v-flex pa-0 pb-2>
                      <vdf-refinement-list
                        title="Organizator"
                        attribute="organizer"
                      />
                    </v-flex>
                  </v-col>
                </v-row>
              </v-container>
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
      </v-content>
      <!-- <vdf-footer />-->
    </ais-instant-search-ssr>
  </v-app>
</template>

<script>
import { AisHits, AisInstantSearchSsr } from 'vue-instantsearch'
import VdfHits from '../components/Hits'
import VdfHeader from '~/components/Header'
import VdfFooter from '~/components/Footer'
import VdfRefinementList from '../components/search/VdfRefinementList'
import VdfSportMenu from '../components/search/VdfSportMenu'

export default {
  components: {
    VdfRefinementList,
    VdfSportMenu,
    AisInstantSearchSsr,
    VdfHeader,
    VdfFooter,
    AisHits,
    VdfHits,
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
