<template>
  <v-layout column>
    <v-flex py-0 my-0>
      <v-layout row>
        <v-flex my-0 py-0>
          <v-switch v-model="showPastEvents" label="Evenimente trecute"></v-switch>
        </v-flex>
      </v-layout>
    </v-flex>
  </v-layout>
</template>

<script>
  import {Component} from 'vue-instantsearch'

  const facetName = 'showPastEvents'

  export default {
    name: 'vdf-misc-filter',
    mixins: [Component],
    data() {
      return {
        showPastEvents: false
      }
    },
    created() {
      this.searchStore.addFacet(facetName)
      this.searchStore.algoliaHelper.addFacetRefinement(facetName, this.showPastEvents)
    },
    watch: {
      showPastEvents(val) {
        this.searchStore.stop()
        this.searchStore.algoliaHelper.removeFacetRefinement(facetName)
        this.searchStore.algoliaHelper.addFacetRefinement(facetName, val)
        this.searchStore.start()
        this.searchStore.refresh()
      }
    }
  }
</script>

<style>
</style>
