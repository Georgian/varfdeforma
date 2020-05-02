<template>
  <v-app-bar app fixed clipped-left>
    <vue-progress-bar />
    <div class="hidden-lg-and-up ma-0 pa-0">
      <v-app-bar-nav-icon
        v-if="showDrawerIcon"
        @click="showHideDrawer"
      ></v-app-bar-nav-icon>
    </div>
    <v-toolbar-title>
      <router-link to="/">
        <span id="site-title" class="title"
          >Vârf de Formă<sup style="color: indianred; font-size: 12px;"
            >ALPHA</sup
          ></span
        >
      </router-link>
    </v-toolbar-title>
    <v-flex v-if="isHomePage" mt-7 ml-2>
      <vdf-debounced-search-box />
    </v-flex>
    <vdf-display-mode-switch v-if="isHomePage" />
    <v-spacer />
    <v-toolbar-items>
      <v-btn v-if="!isAuthenticated" text @click="goToLoginPage()">
        <font-awesome-icon icon="sign-in-alt" />
        Login
      </v-btn>
      <v-btn v-if="isAuthenticated" text @click="logout()">Logout</v-btn>
    </v-toolbar-items>
  </v-app-bar>
</template>

<script>
import VdfDebouncedSearchBox from './search/VdfDebouncedSearchBox'
import VdfDisplayModeSwitch from './DisplayModeSwitch'

export default {
  name: 'VdfHeader',
  components: {
    VdfDisplayModeSwitch,
    VdfDebouncedSearchBox,
  },
  props: {
    showDrawerIcon: {
      type: Boolean,
      default: false,
    },
  },
  computed: {
    isAuthenticated() {
      return this.$store.getters['modules/auth/isAuthenticated']
    },
    isHomePage() {
      return this.$route.name === 'index'
    },
  },
  methods: {
    showHideDrawer() {
      // this.$store.dispatch('showHideDrawer')
      this.$eventBus.$emit('toggleDrawer')
    },
    goToLoginPage() {
      this.$router.replace('/login')
    },
    logout() {
      this.$store.dispatch('modules/auth/logout')
      this.$router.replace('/')
    },
  },
}
</script>

<style scoped></style>
