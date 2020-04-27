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

// https://github.com/algolia/react-instantsearch/issues/2847#issuecomment-535476270
const reqCache = new Map()
const promiseCache = new Map()

const doRequest = (body) =>
  fetch('http://localhost:8080/search', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body,
  }).then((res) => res.json())

const searchClient = {
  async search(requests) {
    const body = JSON.stringify(requests[0].params)
    const cached = reqCache.get(body)
    if (cached) return cached

    const promiseCached = promiseCache.get(body)
    if (promiseCached) return promiseCached

    console.log(body)
    const promise = doRequest(body)
    promiseCache.set(body, promise)

    const response = await promise
    reqCache.set(body, response)

    promiseCache.delete(body)

    return response
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
