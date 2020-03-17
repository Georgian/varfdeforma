<template>
  <div>
    <map-loader
      :map-config="mapConfig"
    >
      <template v-for="marker in vdfEvents">
        <child-marker :position="coordinateStringToObject(marker.locationCoordinates)"></child-marker>
      </template>
    </map-loader>
  </div>
</template>

<script>
import MapLoader from './MapLoader.vue'
import ChildMarker from './ChildMarker'

export default {
  name: 'vdf-map',
  props: {
    vdfEvents: Array
  },
  data(){
    return {
      mapConfig: {
        zoom: 7,
        center: { lat: 45.919849, lng: 25.0203875 }
      }
    }
  },
  components: {
    MapLoader,
    ChildMarker
  },
  methods: {
    coordinateStringToObject: function (coordinateString) {
      let split = coordinateString.split(',')
      return { lat: parseFloat(split[0]), lng: parseFloat(split[1]) }
    }
  }
}
</script>
