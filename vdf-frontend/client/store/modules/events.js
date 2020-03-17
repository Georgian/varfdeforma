const today = new Date()
const searchableFields = ['name', 'description', 'discipline', 'organizer', 'locationName']
const strMatches = function (a, b) { return a && a.toLowerCase().includes(b) }
const matchesQuery = function (event, query) { return searchableFields.some(field => strMatches(event[field], query.toLowerCase())) }

const state = () => ({
  isLoading: false,
  events: [],
})

const mutations = {
  SET_EVENTS(state, events) {
    state.events = events
  },
  SET_LOADING(state, isLoading) {
    state.isLoading = isLoading
  },
}

const getters = {
  events: state => state.events,
  eventsByFacets: state => facets => {
    return state.events.filter(event => {
      let showPastEvents = (facets.showPastEvents && facets.showPastEvents[0] === 'true')
      if (!showPastEvents && event.isPastEvent()) {
        return false
      }

      let sports = facets.sport
      if (sports && !sports.some(s => event.tags.map(t => t.category).indexOf(s) >= 0)) {
        return false
      }

      let miscs = facets.miscellaneous
      if (miscs && !miscs.some(s => event.tags.map(t => t.name).indexOf(s) >= 0)) {
        return false
      }

      let disciplines = facets.discipline
      if (disciplines && !disciplines.some(s => event.tags.map(t => t.name).indexOf(s) >= 0)) {
        return false
      }

      let organizer = facets.organizer
      if (organizer && !organizer.includes(event.organizer)) {
        return false
      }

      let query = facets.query
      if (query && !matchesQuery(event, query)) {
        return false
      }

      return true
    })
      .sort(function (a, b) {
        return new Date(a.dateStart) - new Date(b.dateStart)
      })
  }
}

const actions = {
  loadEvents({commit}) {
    commit('SET_LOADING', true)
    this.$axios
      .get('/event')
      .then(r => r.data)
      .then(events => {
        events.forEach(e => {
          e.isPastEvent = function () {
            let refDate = e.dateEnd ? e.dateEnd : e.dateStart
            return new Date(refDate) < today
          }
        })

        commit('SET_EVENTS', events)
        commit('SET_LOADING', false)
      })
  },
}

export default {
  state,
  actions,
  mutations,
  getters,
  namespaced: true
}
