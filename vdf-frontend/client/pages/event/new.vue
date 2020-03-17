<template>
  <v-content>
    <v-container>
      <v-layout v-if="vdfEvent" column>
        <v-text-field
          v-model="fbEventLink"
          label="Event Link"
          outline
        ></v-text-field>
        <v-text-field
          v-model="vdfEvent.name"
          label="Name"
          outline
        ></v-text-field>
        <v-text-field
          v-model="vdfEvent.organizer"
          label="Organizer"
          outline
        ></v-text-field>
        <v-flex>
          <no-ssr>
            <v-date-picker v-model="selectedDate" is-expanded mode="range">
              <v-text-field
                slot-scope="props"
                v-model="selectedDateText"
                readonly
                label="Date"
                outline
                @change.native="props.updateValue($event.target.value)"
              >
              </v-text-field>
            </v-date-picker>
          </no-ssr>
        </v-flex>
        <v-layout row>
          <v-text-field
            v-model="vdfEvent.locationName"
            label="Location"
            outline
          ></v-text-field>
          <v-text-field
            v-model="vdfEvent.locationCoordinates"
            label="Coordinates"
            outline
          ></v-text-field>
        </v-layout>
        <v-layout row wrap>
          <v-flex v-for="tag in allTags" :key="tag.name" md2 sm4 xs10>
            <v-checkbox
              :label="tag.name"
              v-model="selectedTags"
              :value="tag"
            ></v-checkbox>
          </v-flex>
        </v-layout>
        <v-textarea
          v-model="vdfEvent.description"
          label="Description"
          rows="20"
          outline
        ></v-textarea>
        <v-textarea
          v-model="vdfEvent.tracks"
          label="Traseu"
          rows="10"
          outline
        ></v-textarea>
        <v-textarea
          v-model="vdfEvent.prizes"
          label="Premii"
          rows="10"
          outline
        ></v-textarea>
        <v-textarea
          v-model="vdfEvent.schedule"
          label="Program"
          rows="10"
          outline
        ></v-textarea>
        <v-textarea
          v-model="vdfEvent.registrationTax"
          label="Taxă"
          rows="10"
          outline
        ></v-textarea>
        <v-textarea
          v-model="vdfEvent.ageCategories"
          label="Categorii"
          rows="10"
          outline
        ></v-textarea>
        <v-text-field
          v-model="vdfEvent.photoLink"
          label="Photo Link"
          outline
        ></v-text-field>
        <v-text-field
          v-model="vdfEvent.registrationLink"
          label="Registration Link"
          outline
        ></v-text-field>
        <v-btn @click="save">SAVE</v-btn>
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
import Vue from 'vue'

export default {
  name: 'EventEditPage',
  middleware: 'unauth-redirect',
  data: function() {
    return {
      vdfEvent: {},
      selectedTags: [],
      allTags: null,
      fbEventLink: null,
      isFBReady: false,
      selectedDate: {
        start: null,
        end: null
      },
      selectedDateText: null
    }
  },
  watch: {
    selectedDate: function(newVal, oldVal) {
      this.selectedDateText = this.formatDate(newVal.start, newVal.end)
    }
  },
  mounted: function() {
    this.isFBReady = Vue.FB != undefined
    window.addEventListener('fb-sdk-ready', this.onFBReady)
  },
  created() {
    let self = this
    this.$axios.get('/event/tags').then(response => {
      console.log(response.data)
      self.allTags = response.data
    })
  },
  beforeDestroy: function() {
    window.removeEventListener('fb-sdk-ready', this.onFBReady)
  },
  methods: {
    onFBReady: function() {
      this.isFBReady = true
    },
    hasTag: function(tag) {
      return this.vdfEvent.tags.includes(tag)
    },
    save: function() {
      this.vdfEvent.dateStart = this.formatDateISO(this.selectedDate.start)
      this.vdfEvent.dateEnd = this.formatDateISO(this.selectedDate.end)
      this.vdfEvent.tags = this.selectedTags
      this.$axios
        .post('/event/', this.vdfEvent)
        .then(respone => {
          confirm('Saved!')
          this.$router.push('/')
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped></style>
