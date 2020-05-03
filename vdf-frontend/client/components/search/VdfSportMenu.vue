<template>
  <v-card class="pb-2 mb-2">
    <v-flex>
      <div class="overline">Sport</div>
    </v-flex>
    <v-radio-group :v-model="radioValue" :mandatory="true" hide-details>
      <v-list-item>
        <v-radio label="Toate" @change="state.refine()" />
      </v-list-item>
      <v-list-item v-for="item in items" :key="item.value">
        <v-radio
          :label="item.label"
          :value="item.value"
          @change="state.refine(item.value)"
        />
      </v-list-item>
    </v-radio-group>
  </v-card>
</template>

<script>
import { createWidgetMixin } from 'vue-instantsearch'
import { connectMenu } from 'instantsearch.js/es/connectors'

export default {
  name: 'VdfSportMenu',
  mixins: [createWidgetMixin({ connector: connectMenu })],
  props: {
    title: {
      type: String,
      required: true,
    },
    attribute: {
      type: String,
      required: true,
    },
  },
  data: () => ({
    radioValue: 'Toate',
  }),
  computed: {
    widgetParams() {
      return {
        attribute: this.attribute,
        sortBy: ['count:desc', 'name:asc'],
      }
    },
    items() {
      return this.state.items
    },
  },
}
</script>

<style scoped>
.v-list-item {
  min-height: 36px;
}
.v-input--selection-controls {
  margin-top: 0;
  padding-top: 0;
}
</style>
