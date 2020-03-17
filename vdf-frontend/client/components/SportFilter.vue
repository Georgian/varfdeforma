<template>
  <v-radio-group v-model="radioGroup" :mandatory="true" class="large">
    <v-radio label="Toate" value="radio-1" @change="showOnly('All')"></v-radio>
    <v-radio label="MTB" value="radio-2" @change="showOnly('MTB')"></v-radio>
    <v-radio
      label="Șosea"
      value="radio-3"
      @change="showOnly('Șosea')"
    ></v-radio>
    <v-radio
      label="Ciclocross"
      value="radio-4"
      @change="showOnly('Ciclocross')"
    ></v-radio>
  </v-radio-group>
</template>
<script>
import { Component } from 'vue-instantsearch'

export default {
  name: 'VdfSportFilter',
  mixins: [Component],
  data() {
    return {
      radioGroup: 'radio-1'
    }
  },
  watch: {
    radioGroup: function(oldRadioGroup, newRadioGroup) {
      console.log(newRadioGroup)
    }
  },
  methods: {
    showOnly: function(sport) {
      this.searchStore.stop()
      this.searchStore.algoliaHelper.removeFacetRefinement('sport')
      if (sport !== 'All') {
        this.searchStore.algoliaHelper.addFacetRefinement('sport', sport)
      }
      this.searchStore.start()
      this.searchStore.refresh()
    }
  }
}
</script>

<style>
.v-radio .v-label {
  font-size: 14px;
}
</style>
