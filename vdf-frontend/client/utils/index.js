// import jwtDecode from 'jwt-decode'
// import cookieparser from 'js-cookie'
//
// export function getUserFromCookie (req) {
//   if (!req.headers.cookie) return
//
//   if (req.headers.cookie) {
//     const parsed = cookieparser.parse(req.headers.cookie) // TODO
//     const accessTokenCookie = parsed.access_token
//     if (!accessTokenCookie) return
//
//     const decodedToken = jwtDecode(accessTokenCookie)
//     if (!decodedToken) return
//
//     return decodedToken
//   }
// }
