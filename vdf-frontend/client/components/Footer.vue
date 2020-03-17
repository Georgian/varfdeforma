<template>
  <v-footer height="100px" class="text-xs-center" absolute>
    <v-layout column>
      <!--<v-flex mt-3 mb-1>
        Un proiect pilot dezvoltat în timpul liber, din pasiune pentru ciclism și programare <font-awesome-icon icon="heart"></font-awesome-icon>
      </v-flex>-->
      <v-flex mb-1>
        <a href="https://goo.gl/forms/IUP6RC6D4lfGVyx92" style="text-decoration: none;" class="blue--text" target="_blank" rel="noopener">
          Informații greșite? Contribuie aici <font-awesome-icon icon="heart"></font-awesome-icon>
        </a>
      </v-flex>
      <v-flex mb-3>
        &copy;2018
        — <strong>varfdeforma.ro</strong>
        — <router-link to="/termeni" style="text-decoration: none;" class="blue--text">Termeni și Condiții</router-link>
        — Contact: <strong>admin@varfdeforma.ro</strong>
      </v-flex>
    </v-layout>
  </v-footer>
</template>

<script>
export default {
  name: 'vdf-footer'
}
</script>

<style scoped>
</style>
