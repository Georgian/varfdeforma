<template>
  <v-layout justify-center align-center>
    <v-card class="elevation-10" style="flex: 0 1 400px">
      <v-card-title class="headline">Sign Up</v-card-title>
      <v-card-text>
        <v-form @submit.prevent="signup">
          <v-alert v-if="alert" :type="alert.type" value="true">{{alert.message}}</v-alert>
          <v-text-field label="Name" v-model="name"></v-text-field>
          <v-text-field label="Email" v-model="email"></v-text-field>
          <v-text-field label="Password" v-model="password" type="password"></v-text-field>
          <v-btn type="submit" :loading="loading" :disabled="loading">Sign Up</v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </v-layout>
</template>

<script>
export default {
  name: 'Register',
  data: function () {
    return {
      name: '',
      email: '',
      password: '',
      alert: null,
      loading: false,
      facebook_loading: false,
      facebook_ready: true
    }
  },
  methods: {
    signup () {
      this.alert = null
      this.loading = true
      this.$store.dispatch('signupUser', {
        name: this.name,
        email: this.email,
        password: this.password
      }).then(result => {
        console.log(result.data)
        this.alert = {type: 'success', message: result.data.message}
        this.loading = false
      }).catch(err => {
        console.log(err.response.data.message)
        this.loading = false
        if (err.response && err.response.data) {
          this.alert = {type: 'error', message: err.response.data.message || err.response.status}
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
