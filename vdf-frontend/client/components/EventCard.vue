<template>
  <v-card height="430px" class="flexcard">
    <img
      v-lazy="vdfEvent.photoLink"
      :style="vdfEvent.isPastEvent() ? 'filter: grayscale(100%);' : ''"
      height="200px"
    />

    <v-flex pb-0>
      <a :href="`/event/${vdfEvent.id}`"
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

    <v-layout row wrap align-start justify-start>
      <v-flex>
        <v-chip v-for="tag in vdfEvent.tags" :key="tag.name" label>
          <v-img :src="trophyIcon"></v-img>
          {{ tag.name }}
        </v-chip>
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>
export default {
  props: {
    vdfEvent: { type: Object, required: true }
  },
  data() {
    return {
      trophyIcon: '/trophy.png'
    }
  },
  methods: {
    tagLabel(tag) {
      let name = tag.name
    }
  }
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
</style>
