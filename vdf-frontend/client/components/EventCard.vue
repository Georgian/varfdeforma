<template>
  <v-card height="430px" class="flexcard">
    <img v-lazy="vdfEvent.photoLink" height="200px" />

    <v-flex pb-0>
      <a
        ><h2 class="hover-item-text-underline soft-black">
          {{ vdfEvent.name }}
        </h2></a
      >
      <span class="light-gray">
        <font-awesome-icon icon="calendar-alt"></font-awesome-icon>
        {{ $nuxt.formatDate(vdfEvent.dateStart, null) }}
      </span>
      <br />
      <span class="light-gray">
        <font-awesome-icon icon="location-arrow"></font-awesome-icon>
        {{ vdfEvent.locationName }}
      </span>
    </v-flex>

    <v-flex>
      <v-chip-group column>
        <v-chip v-for="tag in vdfEvent.tags" :key="tag.name" label>
          {{ tag.name }}
        </v-chip>
      </v-chip-group>
    </v-flex>
  </v-card>
</template>

<script>
export default {
  props: {
    vdfEvent: { type: Object, required: true },
  },
  data() {
    return {
      trophyIcon: '/trophy.png',
    }
  },
  mounted() {
    console.log(this.vdfEvent)
  },
  methods: {},
}
</script>

<style scoped>
.flexcard {
  display: flex;
  flex-direction: column;
}
.hover-item-text-underline:hover {
  text-decoration: underline;
}
img {
  object-fit: cover;
}
.trophy-tag {
  color: yellow;
}
.debug-border {
  border-style: solid;
  border-width: 2px;
}
</style>
