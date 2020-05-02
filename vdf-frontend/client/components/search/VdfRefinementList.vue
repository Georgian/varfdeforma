<template>
  <v-flex>
    <v-row v-for="item in items" :key="item.value">
      <v-checkbox
        class="ais-RefinementList-label"
        hide-details
        :label="item.label"
        @change="state.refine(item.value)"
      />
      <span class="ais-RefinementList-count">{{ item.count }}</span>
    </v-row>
  </v-flex>
</template>

<script>
import { createWidgetMixin } from 'vue-instantsearch'
import { connectRefinementList } from 'instantsearch.js/es/connectors'

export default {
  name: 'VdfRefinementList',
  mixins: [createWidgetMixin({ connector: connectRefinementList })],
  props: {
    attribute: {
      type: String,
      required: true,
    },
    // :sort-by="['count:desc', 'name:asc']
  },
  computed: {
    widgetParams() {
      return {
        attribute: this.attribute,
      }
    },
    items() {
      return this.state.items
    },
  },
}
</script>

<style scoped>
.v-input--selection-controls {
  padding: 0;
  margin: 0;
}
.v-application ul {
  padding-left: 0;
}
.ais-RefinementList-item {
  list-style: none;
}
.ais-RefinementList-count {
  float: right;
  align-items: center;
  background-color: rgba(65, 66, 71, 0.08);
  border-radius: 4px;
  color: rgba(33, 36, 61, 0.8);
  display: flex;
  font-size: 0.64rem;
  font-weight: 600;
  letter-spacing: 1.1px;
  margin-left: 8px;
  padding: 0 4px;
}
.ais-RefinementList-label {
  align-items: center;
  display: flex;
}
</style>
