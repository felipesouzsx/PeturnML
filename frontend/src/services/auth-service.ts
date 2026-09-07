import AuthenticationError from '@/errors/authentication-error';
import { useAuthStore } from '@/stores/auth-store';
import type { UserModel } from '@/model/user-model';
import { authenticatedFetch } from './api-service';

interface RegisterRequest {
  username: string;
  email: string;
  password: string;
}

export async function login(email: string, password: string) {
  const response = await authenticatedFetch('/auth/login', {
    info: {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: email,
        password: password,
      }),
    },
  });
  if (!response.ok) {
    throw new AuthenticationError('Bad credentials');
  }

  const authStore = useAuthStore();
  const user: UserModel = await response.json();
  authStore.setUser(user);
}

export async function register(username: string, email: string, password: string): Promise<void> {
  const request: RegisterRequest = {
    username,
    email,
    password,
  };

  const response = await authenticatedFetch('/auth/register', {
    info: {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(request),
    },
  });

  if (!response.ok) {
    throw new AuthenticationError('Registration failed');
  }
}
