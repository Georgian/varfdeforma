<template>
  <v-layout justify-center align-center>
    <v-card class="elevation-10" style="flex: 0 1 400px">
      <v-card-title class="headline">Log In</v-card-title>
      <v-card-text>
        <v-form @submit.prevent="loginUser">
          <v-alert v-if="alert" :type="alert.type" value="true">{{alert.message}}</v-alert>
          <v-text-field label="Email" v-model="email"></v-text-field>
          <v-text-field label="Password" v-model="password" type="password"></v-text-field>
          <v-btn type="submit" :loading="loading" :disabled="loading">Log In</v-btn>
          <v-btn
            :href="`${$axios.defaults.baseURL}/oauth2/authorize/facebook?redirect_uri=${redirect_uri}`"
            color="blue white--text" v-if="facebook_ready" :loading="facebook_loading" :disabled="facebook_loading">Log in with Facebook</v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </v-layout>
</template>

<script>
export default {
  mounted () {
    if (this.$store.getters.isAuthenticated)
      this.$router.replace('/')
  },
  middleware: 'auth-redirect',
  data: function () {
    return {
      email: '',
      password: '',
      alert: null,
      loading: false,
      facebook_loading: false,
      facebook_ready: true,
      redirect_uri: process.env.baseFrontendURL + '/signed-in'
    }
  },
  name: 'Login',
  methods: {
    loginUser() {
      this.alert = null
      this.loading = true
      this.$store.dispatch('auth/login', {
        email: this.email,
        password: this.password
      }).then(result => {
        console.log(result.data)
        this.alert = {type: 'success', message: result.data.message}
        this.loading = false
        this.$router.push('/')
      }).catch(err => {
        this.loading = false
        if (err.response && err.response.data) {
          console.log(err.response.data.message)
          this.alert = {type: 'error', message: err.response.data.message || err.response.status}
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
