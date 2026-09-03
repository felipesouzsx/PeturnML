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

export async function authenticatedFetch(
  url: string,
  info: RequestInit = { method: 'GET' },
  params: URLSearchParams | null = null,
) {
  const csrfToken = getCsrfToken();
  const headers = info.headers ? new Headers(info.headers) : new Headers();
  headers.set('X-XSRF-TOKEN', csrfToken);

  return fetch(`${apiUrl(url)}?${params ? params : ''}`, {
    ...info,
    credentials: 'include',
    headers,
  });
}
