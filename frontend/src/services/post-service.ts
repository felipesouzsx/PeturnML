import { authenticatedFetch } from './api-service';
import PostError from '@/errors/post-error';
import type Post from '@/model/post';
import { PostStatus } from '@/model/post-status';

export async function loadPosts(page: number): Promise<Array<Post>> {
  const response = await authenticatedFetch('/posts');
  if (!response.ok) {
    return [];
  }
  const json = await response.json();
  return json;
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
