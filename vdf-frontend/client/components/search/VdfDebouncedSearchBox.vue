<!-- Debounced search field -->
<!-- Courtesy of https://github.com/minazeTTK/myVNDB/blob/master/src/components/extra/components/DebouncedSearchBox.vue -->
<template>
  <v-text-field
    v-model="query"
    solo-inverted
    label="Caută eveniment"
    prepend-inner-icon="search"
    hide-details
    flat
    class="mr-2"
  />
</template>

<script>
import { createWidgetMixin } from 'vue-instantsearch'
import { connectSearchBox } from 'instantsearch.js/es/connectors'

export default {
  mixins: [createWidgetMixin({ connector: connectSearchBox })],
  props: {
    delay: {
      type: Number,
      default: 200,
      required: false,
    },
  },
  data() {
    return {
      timerId: null,
      localQuery: '',
    }
  },
  computed: {
    query: {
      get() {
        return this.localQuery
      },
      set(val) {
        this.localQuery = val
        if (this.timerId) {
          clearTimeout(this.timerId)
        }
        this.timerId = setTimeout(() => {
          this.state.refine(this.localQuery)
        }, this.delay)
      },
    },
  },
  mounted() {},
  destroyed() {
    if (this.timerId) {
      clearTimeout(this.timerId)
    }
  },
}
</script>

<style scoped></style>
