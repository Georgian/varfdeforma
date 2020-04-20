<template>
  <v-content>
    <v-container grid-list-xl>
      <v-layout row wrap>
        <ais-instant-search-ssr>
          <ais-search-box />
          <ais-hits>
            <template slot="item" slot-scope="{ item }">
              hi
            </template>
          </ais-hits>
        </ais-instant-search-ssr>
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
import {
  AisInstantSearchSsr,
  AisHits,
  AisSearchBox,
  createInstantSearch,
} from 'vue-instantsearch'
import createSearchStoreFromVuex from '~/plugins/search.js'

const searchClient = {
  search(requests) {
    var aisResultObject = {
      hits: [],
      facets: {
        sport: {
          MTB: 1,
        },
      },
      nbHits: 1,
      page: 0,
      nbPages: 1,
      hitsPerPage: 20,
      processingTimeMS: 3,
      exhaustiveNbHits: true,
      query: 'playstation4 (500gb) us ',
      params:
        'query=playstation4%20(500gb)%20us%20&page=0&highlightPreTag=__ais-highlight__&highlightPostTag=__%2Fais-highlight__&facets=%5B%5D&tagFilters=',
      index: 'vdf',
    }

    var result = {
      results: [aisResultObject, aisResultObject],
    }

    return Promise.resolve(result)
  },
}

// const searchClient = createSearchStoreFromVuex($store)

const { instantsearch, rootMixin } = createInstantSearch({
  searchClient,
  indexName: 'vdf',
})

export default {
  components: {
    AisInstantSearchSsr,
    AisHits,
    AisSearchBox,
  },
  mixins: [rootMixin],
  asyncData() {
    return instantsearch
      .findResultsState({
        query: 'triada mtb',
        hitsPerPage: 1,
        disjunctiveFacets: ['sport'],
        disjunctiveFacetsRefinements: { sport: ['MTB'] },
      })
      .then(() => ({
        algoliaState: instantsearch.getState(),
      }))
  },
  beforeMount() {
    // Nuxt will merge `asyncData` and `data` on the client
    instantsearch.hydrate(this.algoliaState)
  },
  head() {
    return {
      link: [
        {
          rel: 'stylesheet',
          href:
            'https://unpkg.com/instantsearch.css@7.1.0/themes/algolia-min.css',
        },
      ],
    }
  },
}
</script>

<style></style>
