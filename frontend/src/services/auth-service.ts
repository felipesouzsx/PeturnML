import AuthenticationError from '@/errors/authentication-error'
import type { LoginResponse } from '@/model/login-response'
import { getCookie } from './cookie-service'

interface RegisterRequest {
  username: string
  email: string
  password: string
}

export async function login(email: string, password: string): Promise<LoginResponse> {
  const CsrfToken = getCsrfToken()
  const response = await fetch('http://localhost:8080/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'X-XSRF-TOKEN': CsrfToken,
    },
    credentials: 'include',
    body: JSON.stringify({
      email: email,
      password: password,
    }),
  })
  if (!response.ok) {
    throw new AuthenticationError('Bad credentials')
  }
  return { accessToken: 'aaaa', user: { email: 'aaaa', id: '1234', name: 'John Doe' } }
}

export async function register(username: string, email: string, password: string): Promise<void> {
  const request: RegisterRequest = {
    username,
    email,
    password,
  }

  const token = getCsrfToken()

  const response = await fetch('http://localhost:8080/auth/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'X-XSRF-TOKEN': token,
    },
    credentials: 'include',
    body: JSON.stringify(request),
  })

  if (!response.ok) {
    throw new AuthenticationError('Registration failed')
  }
}

function getCsrfToken(): string {
  const token = getCookie('XSRF-TOKEN')
  if (!token) {
    throw new AuthenticationError('Registration failed')
  }
  return token
}
