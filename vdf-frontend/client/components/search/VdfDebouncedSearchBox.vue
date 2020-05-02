<!-- Debounced search field -->
<!-- Courtesy of https://github.com/minazeTTK/myVNDB/blob/master/src/components/extra/components/DebouncedSearchBox.vue -->
<template>
  <v-text-field
    solo-inverted
    label="Caută eveniment"
    v-model="query"
    prepend-inner-icon="search"
  >
  </v-text-field>
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

<style scoped>
.compact-form {
  transform: scale(0.875);
  transform-origin: left;
}
</style>
