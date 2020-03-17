<template>

</template>
<script>
import Vue from 'vue'
import InfoWindow from './InfoWindow'

Vue.use(InfoWindow)

export default {
  inject: ['google', 'map'],
  props: {
    position: Object
  },
  data () {
    return {
      marker: null,
      infoWindow: null
    }
  },
  mounted () {
    const self = this
    const { Marker, InfoWindow } = this.google.maps
    self.marker = new Marker({
      position: self.position,
      map: self.map,
      title: 'Child marker!'
    })
    self.infoWindow = new InfoWindow({
      content: 'shit'
    })
    this.marker.addListener('click', function () {
      self.infoWindow.open(self.map, self.marker)
    })
  }
}
</script>
