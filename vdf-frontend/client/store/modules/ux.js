const ViewMode = {
  CARDS: 'cards',
  MAP: 'map',
}
Object.freeze(ViewMode)

const state = () => ({
  viewMode: ViewMode.CARDS,
})

const getters = {
  getViewMode(state) {
    return state.viewMode
  },
  getNextViewMode(state) {
    // TODO lol
    switch (state.viewMode) {
      case ViewMode.CARDS:
        return ViewMode.MAP
      default:
      case ViewMode.MAP:
        return ViewMode.CARDS
    }
  },
}

const actions = {
  setNextViewMode({ commit, getters }) {
    commit('SET_VIEW_MODE', getters.getNextViewMode)
  },
}

const mutations = {
  SET_VIEW_MODE: function (state, viewMode) {
    state.viewMode = viewMode
  },
}

export default {
  state,
  mutations,
  actions,
  getters,
  namespaced: true,
}
