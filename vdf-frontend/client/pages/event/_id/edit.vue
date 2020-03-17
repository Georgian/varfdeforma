<template>
  <v-container>
    <v-layout v-if="vdfEvent" column>
      <v-text-field v-model="vdfEvent.name" label="Name" outline></v-text-field>
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
</template>

<script>
export default {
  name: 'EventEditPage',
  middleware: 'unauth-redirect',
  data: function() {
    return {
      vdfEvent: null,
      selectedTags: null,
      allTags: null
    }
  },
  watch: {
    // Call again the method if the route changes
    $route: 'fetchVdfEvent'
  },
  created() {
    this.fetchVdfEvent()
  },
  methods: {
    hasTag: function(tag) {
      return this.vdfEvent.tags.includes(tag)
    },
    save: function() {
      this.vdfEvent.tags = this.selectedTags
      this.$axios
        .put('/event/' + this.vdfEvent.id, this.vdfEvent)
        .then(respone => {
          confirm('Saved!')
          this.$router.push('/')
        })
        .catch(err => {
          console.log(err)
        })
    },
    fetchVdfEvent: function() {
      this.error = this.vdfEvent = null
      let self = this
      this.$axios.get('/event/' + this.$route.params.id).then(response => {
        // self.loadingData = false
        self.vdfEvent = response.data
        this.computeEventDates(self.vdfEvent)

        this.$axios.get('/event/tags').then(response => {
          self.allTags = response.data
          this.selectedTags = self.vdfEvent.tags
        })
      })
    },
    computeEventDates: function(vdfEvent) {
      this.vdfEventDates = this.formatDate(vdfEvent.dateStart, vdfEvent.dateEnd)
    }
  }
}
</script>

<style scoped></style>
