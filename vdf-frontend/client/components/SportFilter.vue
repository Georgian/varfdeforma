<template>
  <v-radio-group :mandatory="true" class="large">
<!--    <v-flex v-for="item in items">-->
<!--      <v-radio-->
<!--        label="{{item.label}}"-->
<!--        value="{{item.label}}"-->
<!--        @change="instantSearchState.refine(item.value)"-->
<!--      />-->
<!--    </v-flex>-->
  </v-radio-group>
</template>
<script>
import { createWidgetMixin } from 'vue-instantsearch'
import { connectRefinementList } from 'instantsearch.js/es/connectors'

export default {
  name: 'VdfSportFilter',
  mixins: [createWidgetMixin({ connector: connectRefinementList })],
  props: {
    attribute: {
      type: String,
      required: true,
    },
  },
  computed: {
    widgetParams() {
      return {
        attribute: this.attribute,
      }
    },
    items() {
      return this.instantSearchState.items
    },
  },
}
</script>

<style>
.v-radio .v-label {
  font-size: 14px;
}
</style>
