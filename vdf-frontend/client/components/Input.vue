<template>
  <v-text-field
    v-model.lazy="query"
    solo-inverted
    flat
    label="Caută eveniment"
    prepend-inner-icon="search">
  </v-text-field>
</template>

<script>
import { Component } from 'vue-instantsearch'

export default {
  mixins: [Component],
  computed: {
    query: {
      get () {
        return this.searchStore.query
      },
      set (value) {
        this.searchStore.query = value
        this.$emit('query', value)
      }
    }
  }
}
</script>

<style scoped>
  .compact-form {
    transform: scale(0.875);
    transform-origin: left;
  }
</style>
