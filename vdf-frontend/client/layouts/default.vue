<template>
  <v-app id="vdf">
    <!-- When on search page -->
    <v-flex v-if="enableAisIndex">
      <ais-index :search-store="searchStore" index-name="vdf">
        <vdf-nav-drawer />
        <vdf-header show-drawer-icon />
        <nuxt />
        <vdf-footer />
      </ais-index>
    </v-flex>

    <!-- When not on search page -->
    <v-flex v-if="!enableAisIndex">
      <vdf-header :show-drawer-icon="false" />
      <nuxt />
      <vdf-footer />
    </v-flex>

    <no-ssr>
      <cookie-law
        theme="blood-orange"
        button-text="Am înțeles!"
        button-link-text=""
      >
        <div slot="message">
          Acest website folosește cookie-uri pentru a furniza vizitatorilor o
          experiență mult mai bună de navigare și servicii adaptate nevoilor și
          interesului fiecăruia. Citește
          <router-link to="/termeni" class="white--text"
            >Termeni și Condiții</router-link
          >
        </div>
      </cookie-law>
    </no-ssr>
  </v-app>
</template>

<script>
import VdfHeader from '~/components/Header'
import VdfFooter from '~/components/Footer'
import VdfNavDrawer from '~/components/NavDrawer'
import CookieLaw from 'vue-cookie-law'
import createSearchStoreFromVuex from '../plugins/search'

export default {
  components: {
    VdfHeader,
    VdfFooter,
    VdfNavDrawer,
    CookieLaw
  },
  data: () => ({
    searchStore: null
  }),
  computed: {
    enableAisIndex() {
      return this.$route.path === '/'
    },
    eventCount() {
      return this.$store.getters['modules/events/events']
    }
  },
  watch: {
    $route: 'routeChanged',
    eventCount(newCount, oldCount) {
      this.searchStore.refresh()
    }
  },
  created() {
    this.searchStore = createSearchStoreFromVuex(this.$store)
  },
  mounted() {
    this.routeChanged()
  },
  methods: {
    routeChanged() {
      if (this.enableAisIndex) this.$store.dispatch('modules/events/loadEvents')
    }
  }
}
</script>
