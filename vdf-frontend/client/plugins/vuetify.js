import Vue from 'vue'
import Vuetify from 'vuetify'

// TODO This will have to be migrated into the nuxt config, and use a styl file !
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify, {
  theme: {
    primary: '#8860D0'
    // secondary: '#84CEEB',
    // accent: '#8860D0',
    // bckgrnd: '#FAFAFA'
  }
})
