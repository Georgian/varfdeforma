<template>
  <v-container>
    <v-card v-if="loadingData">
      Se încarcă...
    </v-card>

    <v-card v-if="error">
      Eroare!
    </v-card>

    <v-layout v-if="vdfEvent" column>
      <v-flex hidden-md-and-up>
        <v-img :src="vdfEvent.photoLink"></v-img>
      </v-flex>
      <v-layout flex align-center justify-center>
        <v-flex lg6 md6>
          <h1>{{ vdfEvent.name }}</h1>
          <h2>
            <font-awesome-icon icon="calendar-alt"></font-awesome-icon>
            {{ vdfEventDates }}
          </h2>
          <h2>
            <font-awesome-icon icon="location-arrow"></font-awesome-icon>
            {{ vdfEvent.locationName }}
          </h2>
          <a
            href="https://goo.gl/forms/IUP6RC6D4lfGVyx92"
            style="text-decoration: none;"
            class="blue--text"
            target="_blank"
          >
            <font-awesome-icon icon="heart"></font-awesome-icon> Informații
            greșite? Contribuie aici
          </a>
        </v-flex>
        <v-flex lg6 md6 hidden-sm-and-down>
          <v-img :src="vdfEvent.photoLink"></v-img>
        </v-flex>
      </v-layout>

      <v-expansion-panel>
        <v-expansion-panel-content value="true">
          <div slot="header">Descriere</div>
          <v-card>
            <v-card-text>
              <span v-html="handleEmptyField(vdfEvent.description)"></span>
            </v-card-text>
          </v-card>
        </v-expansion-panel-content>

        <v-expansion-panel-content>
          <div slot="header">Traseu</div>
          <v-card>
            <v-card-text>
              <span v-html="handleEmptyField(vdfEvent.tracks)"></span>
            </v-card-text>
          </v-card>
        </v-expansion-panel-content>

        <v-expansion-panel-content>
          <div slot="header">Categorii</div>
          <v-card>
            <v-card-text>
              <span v-html="handleEmptyField(vdfEvent.ageCategories)"></span>
            </v-card-text>
          </v-card>
        </v-expansion-panel-content>

        <v-expansion-panel-content>
          <div slot="header">Program</div>
          <v-card>
            <v-card-text>
              <span v-html="handleEmptyField(vdfEvent.schedule)"></span>
            </v-card-text>
          </v-card>
        </v-expansion-panel-content>

        <v-expansion-panel-content>
          <div slot="header">Taxă</div>
          <v-card>
            <v-card-text>
              <span v-html="handleEmptyField(vdfEvent.registrationTax)"></span>
            </v-card-text>
          </v-card>
        </v-expansion-panel-content>

        <v-expansion-panel-content>
          <div slot="header">Premii</div>
          <v-card>
            <v-card-text>
              <span v-html="handleEmptyField(vdfEvent.prizes)"></span>
            </v-card-text>
          </v-card>
        </v-expansion-panel-content>

        <v-expansion-panel-content>
          <div slot="header">Locație</div>
          <!--
                    <vdf-dir-map :vdf-event="vdfEvent"></vdf-dir-map>
          -->
        </v-expansion-panel-content>

        <v-expansion-panel-content>
          <div slot="header">Meteo</div>
          <!-- TODO -->
          <!--<weather
            api-key="e1ace13f0da11bcdf6a4c8402808822b"
            title="Vremea"
            :latitude="vdfEvent.locationCoordinates.split(',')[0]"
            longitude="vdfEvent.locationCoordinates.split(',')[1]"
            language="en"
            units="uk">
          </weather>-->
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-layout>
  </v-container>
</template>

<script>
import VdfDirMap from '~/components/DirectionsMap'

export default {
  name: 'EventPage',
  components: { VdfDirMap },
  async asyncData({ app, params }) {
    return app.$axios.get('/event/' + params.id).then(res => {
      let vdfEvent = res.data
      return {
        vdfEvent: vdfEvent,
        loadingData: false,
        metaTitle: vdfEvent.name + ' | Vârf de Formă',
        metaDesc: vdfEvent.description
          ? vdfEvent.description.substr(0, 100) + '...'
          : '',
        metaImg: vdfEvent.photoLink
      }
    })
  },
  data: function() {
    return {
      loadingData: false,
      error: null,
      vdfEvent: null,
      vdfEventDates: null,
      metaTitle: 'Vârf de Formă',
      metaDesc: 'Concurs ciclism',
      metaImg: ''
    }
  },
  head() {
    return {
      title: this.metaTitle,
      meta: [
        {
          hid: `og:title`,
          property: 'og:title',
          content: this.metaTitle
        },
        {
          hid: `og:description`,
          property: 'og:description',
          content: this.metaDesc
        },
        {
          hid: `og:image`,
          property: 'og:image',
          content: this.metaImg
        }
      ]
    }
  },
  methods: {
    handleEmptyField: function(field) {
      return field && field !== '' ? field : '<b>Nici o informație</b>'
    }
  }
}
</script>

<style scoped></style>
