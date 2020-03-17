// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueProgressBar from 'vue-progressbar'
import VueLazyload from 'vue-lazyload'
import '~/assets/css/main.css'

// Vue.use(VueGoogleMaps, {
//   load: {
//     key: 'AIzaSyBE69OXwm8z5UD-6Yr29RcvEAyT9fUvSWA',
//     libraries: 'map'
//   }
// })

Vue.prototype.$eventBus = new Vue()

Vue.use(VueLazyload, {
  preLoad: 1.3,
  error: '/no-image.jpg',
  attempt: 1,
  // the default is ['scroll', 'wheel', 'mousewheel', 'resize', 'animationend', 'transitionend']
  listenEvents: [ 'scroll' ]
})

Vue.use(VueProgressBar, {
  color: '#8860D0',
  failedColor: 'red',
  height: '3px'
})

let filter = function (text, length, clamp) {
  clamp = clamp || '...'
  let node = document.createElement('div')
  node.innerHTML = text
  let content = node.textContent
  return content.length > length ? content.slice(0, length) + clamp : content
}
Vue.filter('truncate', filter)

// ----------------------------

// For VDF, I use this to with the ais-index when not on a search page
// See https://stackoverflow.com/questions/43293401/conditionally-rendering-parent-element-keep-inner-html
// TODO make this work and use it !!!!!!
Vue.directive('showButKeepInner', {
  bind (el, bindings) {
    bindings.def.wrap = function (el) {
      // Find all next siblings with data-moved and move back into el
      while (el.nextElementSibling && el.nextElementSibling.dataset.moved) {
        el.appendChild(el.nextElementSibling).removeAttribute('data-moved')
      }
      el.hidden = false
    }

    bindings.def.unwrap = function (el) {
      // Move all children of el outside and mark them with data-moved attr
      Array.from(el.children).forEach(child => {
        el.insertAdjacentElement('afterend', child).setAttribute('data-moved', true)
      })
      el.hidden = true
    }
  },

  inserted (el, bindings) {
    bindings.def[bindings.value ? 'wrap' : 'unwrap'](el)
  },

  update (el, bindings) {
    bindings.def[bindings.value ? 'wrap' : 'unwrap'](el)
  }
})

// ----------------------------

Vue.mixin({
  methods: {
    formatDate: function (dateStartString, dateEndString) {
      let locale = 'ro-RO'
      let options = { weekday: 'long', year: 'numeric', month: 'short', day: 'numeric' }

      let dateStart = new Date(dateStartString)
      var result = dateStart.toLocaleDateString(locale, options)

      if (dateEndString) {
        let formattedDateEndString = new Date(dateEndString).toLocaleDateString(locale, options)
        result = result + ' - ' + formattedDateEndString.charAt(0).toUpperCase() + formattedDateEndString.slice(1)
      }

      return result.charAt(0).toUpperCase() + result.slice(1)
    },
    formatDateISO: function(dateObj) {
      return dateObj.toISOString().slice(0,10);
    }
  }
})
