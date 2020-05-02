<template>
  <v-layout justify-center align-center>
    <v-card class="elevation-10" style="flex: 0 1 400px;">
      <v-card-title class="headline">Log In</v-card-title>
      <v-card-text>
        <v-form @submit.prevent="loginUser">
          <v-alert v-if="alert" :type="alert.type" value="true">{{
            alert.message
          }}</v-alert>
          <v-text-field v-model="email" label="Email"></v-text-field>
          <v-text-field
            v-model="password"
            label="Password"
            type="password"
          ></v-text-field>
          <v-btn type="submit" :loading="loading" :disabled="loading"
            >Log In</v-btn
          >
          <v-btn
            v-if="facebook_ready"
            :href="`${$axios.defaults.baseURL}/oauth2/authorize/facebook?redirect_uri=${redirect_uri}`"
            color="blue white--text"
            :loading="facebook_loading"
            :disabled="facebook_loading"
            >Log in with Facebook</v-btn
          >
        </v-form>
      </v-card-text>
    </v-card>
  </v-layout>
</template>

<script>
export default {
  name: 'Login',
  data: function () {
    return {
      email: '',
      password: '',
      alert: null,
      loading: false,
      facebook_loading: false,
      facebook_ready: true,
      redirect_uri: process.env.baseFrontendURL + '/signed-in',
    }
  },
  mounted() {
    if (this.$store.getters.isAuthenticated) this.$router.replace('/')
  },
  middleware: 'auth-redirect',
  methods: {
    loginUser() {
      this.alert = null
      this.loading = true
      this.$store
        .dispatch('auth/login', {
          email: this.email,
          password: this.password,
        })
        .then((result) => {
          this.alert = { type: 'success', message: result.data.message }
          this.loading = false
          this.$router.push('/')
        })
        .catch((err) => {
          this.loading = false
          if (err.response && err.response.data) {
            console.log(err.response.data.message)
            this.alert = {
              type: 'error',
              message: err.response.data.message || err.response.status,
            }
          }
        })
    },
  },
}
</script>

<style scoped></style>
