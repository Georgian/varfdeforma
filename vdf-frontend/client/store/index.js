import jwtDecode from "jwt-decode";

export const actions = {

  nuxtServerInit({ commit }, { req, app }) {
    try {
      const jwtCookie = req.headers.cookie.split(";").find(c => c.trim().startsWith("token="))
      if (jwtCookie) {
        let token = jwtCookie.split('=')[1]
        let payload = jwtDecode(token)
        let date = Date.now() / 1000
        if (payload.exp > date) {
          commit('modules/auth/SET_USER', payload)
          commit('modules/auth/SET_TOKEN', token)
          app.$axios.setToken(token, 'Bearer')
        }
      }
    } catch (error) {

    }
  }
}
