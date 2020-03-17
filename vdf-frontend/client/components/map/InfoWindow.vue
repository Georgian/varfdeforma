<template>
</template>
<script>
export default {
  name: 'info-window',
  inject: ['google', 'map'],
  data () {
    return {
      infoWindow: null
    }
  },
  mounted () {
    const { InfoWindow } = this.google.maps
    this.infoWindow = new InfoWindow({
      content: '<div>hello</div>',
      position: { lat: 45.919849, lng: 25.0203875 }
    })
    this.infoWindow.open()
  }
}
</script>

<style scoped>

</style>
