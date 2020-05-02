<template>
  <div>
    <ais-refinement-list
      :attribute="facetName"
      :sort-by="['count:desc', 'name:asc']"
    />
    <!--      <template slot-scope="{ refine, items }">-->
    <!--        <v-layout column>-->
    <!--          <v-flex v-for="facet in items" :key="facet.value" my-1 py-1>-->
    <!--            <v-layout row align-center justify-right fill-width>-->
    <!--              <v-checkbox-->
    <!--                v-model="facet.isRefined"-->
    <!--                :label="facet.value"-->
    <!--                class="test my-1"-->
    <!--                hide-details-->
    <!--                @change="refine(facet.value)"-->
    <!--              ></v-checkbox>-->
    <!--              <div class="badge">{{ facet.count }}</div>-->
    <!--            </v-layout>-->
    <!--          </v-flex>-->
    <!--        </v-layout>-->
    <!--      </template>-->
  </div>
</template>

<script>
import AisRefinementList from 'vue-instantsearch'

export default {
  name: 'VdfFacetFilter',
  components: {
    AisRefinementList,
  },
  props: {
    facetName: {
      type: String,
      required: true,
    },
  },
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
