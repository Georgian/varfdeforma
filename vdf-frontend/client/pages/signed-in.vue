<template>
  <p>Signing in...</p>
</template>

<script>
export default {
  mounted () {
    let token = this.$route.query.token

    if (!!token) {
      this.$store.dispatch('modules/auth/loginSuccess', token)
        .then(() => {
          this.$router.replace('/')
        })
    } else {
      console.error('No Token present. Cannot log you in.')
    }
  }
}
</script>

<style scoped>

</style>
