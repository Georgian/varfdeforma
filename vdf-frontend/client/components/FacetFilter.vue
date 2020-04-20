<template>
  <ais-refinement-list
    :attribute-name="facetName"
    :sort-by="['count:desc', 'name:asc']"
    inline-template
  >
    <v-layout column>
      <v-flex v-for="facet in facetValues" :key="facet.name" my-1 py-1>
        <v-layout row align-center justify-right fill-width>
          <v-checkbox
            :label="facet.name"
            v-model="facet.isRefined"
            class="test my-1"
            hide-details
            @change="toggleRefinement(facet)"
          ></v-checkbox>
          <div class="badge ">{{ facet.count }}</div>
        </v-layout>
      </v-flex>
    </v-layout>
  </ais-refinement-list>
</template>

<script>
export default {
  name: 'VdfFacetFilter',
  components: {},
  props: {
    facetName: {
      type: String,
      required: true
    }
  }
}
</script>

<style>
.debug {
  border-style: solid;
}
.test label {
  font-size: 14px;
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
