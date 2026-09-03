import { authenticatedFetch } from './api-service';
import PostError from '@/errors/post-error';
import type PostModel from '@/model/post-model';
import { PostStatus } from '@/model/post-status';

export async function getPosts(page: number, endpoint: string = ''): Promise<Array<PostModel>> {
  const params: URLSearchParams = new URLSearchParams({ page: page.toString() });
  const response = await authenticatedFetch(`/posts${endpoint}`, { method: 'GET' }, params);
  if (!response.ok) {
    return [];
  }
  const json = await response.json();
  // Valor de retorno é um Pageable, o que me interessa tá em content
  return json.content;
}

export async function getOthersPosts(page: number): Promise<Array<PostModel>> {
  return getPosts(0);
}

export async function getYourPosts(page: number): Promise<Array<PostModel>> {
  return getPosts(0, '/me');
}

export async function createPost(
  petName: string,
  petType: string,
  title: string,
  description: string,
  petImage: File,
  status: PostStatus,
) {
  const form: FormData = new FormData();
  form.append('petName', petName);
  form.append('petType', petType);
  form.append('title', title);
  form.append('description', description);
  form.append('petImage', petImage);
  form.append('status', status);

  const response = await authenticatedFetch('/posts', {
    method: 'POST',
    body: form,
  });
  if (!response.ok) {
    throw new PostError(`Failed to create post ${response.statusText}`);
  }
}
