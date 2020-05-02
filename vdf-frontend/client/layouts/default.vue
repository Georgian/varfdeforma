<template>
  <v-app id="vdf">
    <!-- When on search page -->
    <nuxt />

    <client-only>
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
    </client-only>
  </v-app>
</template>

<script>
import CookieLaw from 'vue-cookie-law'

export default {
  components: {
    CookieLaw,
  },
}
</script>
