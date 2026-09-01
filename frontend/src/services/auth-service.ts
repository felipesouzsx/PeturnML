import AuthenticationError from '@/errors/authentication-error';
import { getCookie } from './cookie-service';
import { useAuthStore } from '@/stores/auth-store';
import type { User } from '@/model/user';
import { apiUrl } from '@/config/api';

interface RegisterRequest {
  username: string;
  email: string;
  password: string;
}

export async function login(email: string, password: string) {
  const response = await authenticatedFetch(apiUrl('/auth/login'), {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      email: email,
      password: password,
    }),
  });
  if (!response.ok) {
    throw new AuthenticationError('Bad credentials');
  }

  const authStore = useAuthStore();
  const user: User = await response.json();
  authStore.setUser(user);
}

export async function register(username: string, email: string, password: string): Promise<void> {
  const request: RegisterRequest = {
    username,
    email,
    password,
  };

  const response = await authenticatedFetch(apiUrl('auth/register'), {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(request),
  });

  if (!response.ok) {
    throw new AuthenticationError('Registration failed');
  }
}

function getCsrfToken(): string {
  const token = getCookie('XSRF-TOKEN');
  if (!token) {
    throw new AuthenticationError('Registration failed');
  }
  return token;
}

async function authenticatedFetch(url: string, info: RequestInit) {
  const csrfToken = getCsrfToken();
  const headers = info.headers ? new Headers(info.headers) : new Headers();
  headers.set('X-XSRF-TOKEN', csrfToken);

  return fetch(url, {
    ...info,
    credentials: 'include',
    headers,
  });
}
