<template>
  <v-card class="pb-2 mb-2">
    <v-flex>
      <div class="overline">{{ title }}</div>
    </v-flex>
    <v-list-item v-for="item in items" :key="item.value">
      <v-checkbox
        class="ais-RefinementList-label"
        hide-details
        :label="item.label"
        @change="state.refine(item.value)"
      />
      <span class="ais-RefinementList-count">{{ item.count }}</span>
    </v-list-item>
  </v-card>
</template>

<script>
import { createWidgetMixin } from 'vue-instantsearch'
import { connectRefinementList } from 'instantsearch.js/es/connectors'

export default {
  name: 'VdfRefinementList',
  mixins: [createWidgetMixin({ connector: connectRefinementList })],
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
.v-list-item {
  min-height: 36px;
}
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
.badge {
  float: right;
  min-width: 10px;
  padding: 3px 7px;
  font-size: 10px;
  font-weight: 600;
  line-height: 1;
  color: #fff;
  text-align: center;
  white-space: nowrap;
  background-color: #777;
  border-radius: 8px;
}
</style>
