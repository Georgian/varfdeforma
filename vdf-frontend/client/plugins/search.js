// https://github.com/algolia/react-instantsearch/issues/2847#issuecomment-535476270
import { createInstantSearch } from 'vue-instantsearch'
import algoliaHelper from 'algoliasearch-helper'
import { _objectSpread } from 'vue-instantsearch/src/util/polyfills'

export default ({ app }, inject) => {
  const reqCache = new Map()
  const promiseCache = new Map()

  const doRequest = (body) =>
    fetch('http://localhost:8080/search', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json',
      },
      body,
    }).then((res) => res.json())

  const searchClient = {
    async search(requests) {
      const body = JSON.stringify(requests[0].params)
      const cached = reqCache.get(body)
      if (cached) return cached

      const promiseCached = promiseCache.get(body)
      if (promiseCached) return promiseCached

      const promise = doRequest(body)
      promiseCache.set(body, promise)

      const response = await promise
      reqCache.set(body, response)

      promiseCache.delete(body)

      return response
    },
  }

  const { instantsearch, rootMixin } = createInstantSearch({
    searchClient,
    indexName: 'vdf',
  })

  inject('instantsearch', instantsearch)
  inject('rootMixin', rootMixin)
}
