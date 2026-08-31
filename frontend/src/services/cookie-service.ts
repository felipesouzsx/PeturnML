export function getCookie(name: string) {
  const cookies = document.cookie.split('; ')
  for (let cookie of cookies) {
    const [cookieName, cookieValue] = cookie.split('=')
    if (cookieName == name) {
      return cookieValue
    }
  }
}
