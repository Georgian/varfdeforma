<template>
  <v-card class="pb-2 mb-2">
    <v-flex>
      <div class="overline">Sport</div>
    </v-flex>
    <v-radio-group :v-model="radioValue" :mandatory="true" hide-details>
      <v-list-item>
        <v-radio label="Toate" class="mb-0" @change="state.refine()" />
        <span class="ais-RefinementList-count">{{ totalCount }}</span>
      </v-list-item>
      <v-list-item v-for="item in items" :key="item.value">
        <v-radio
          :label="item.label"
          :value="item.value"
          class="mb-0"
          @change="state.refine(item.value)"
        />
        <span class="ais-RefinementList-count">{{ item.count }}</span>
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
    totalCount() {
      return this.state.items.map((i) => i.count).reduce((a, b) => a + b, 0)
    },
  },
}
</script>

<style scoped>
.custom-label-size {
  font-size: 16px;
}
.v-list-item {
  min-height: 36px;
}
.v-input--selection-controls {
  margin-top: 0;
  padding-top: 0;
}
.ais-RefinementList-count {
  float: right;
  display: flex;
  background-color: rgba(65, 66, 71, 0.08);
  border-radius: 4px;
  color: rgba(33, 36, 61, 0.8);
  font-size: 0.64rem;
  font-weight: 600;
  letter-spacing: 1.1px;
  margin-left: 8px;
  padding: 0 4px;
}
</style>
