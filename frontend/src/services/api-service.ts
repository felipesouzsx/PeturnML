import AuthenticationError from '@/errors/authentication-error';
import { getCookie } from './cookie-service';
import { apiUrl } from '@/config/api';

function getCsrfToken(): string {
  const token = getCookie('XSRF-TOKEN');
  if (!token) {
    throw new AuthenticationError('Registration failed');
  }
  return token;
}

interface FetchOptions {
  info?: RequestInit;
  params?: URLSearchParams | null;
}

export async function authenticatedFetch(url: string, options: FetchOptions = {}) {
  const csrfToken = getCsrfToken();
  const headers = new Headers(options.info?.headers);
  headers.set('X-XSRF-TOKEN', csrfToken);

  return fetch(`${apiUrl(url)}?${options.params ? options.params : ''}`, {
    ...options.info,
    credentials: 'include',
    headers,
  });
}
