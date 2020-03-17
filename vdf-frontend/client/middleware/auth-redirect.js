export default function ({store, redirect}) {
  if (store.getters['modules/auth/isAuthenticated'])
    return redirect('/')
}
