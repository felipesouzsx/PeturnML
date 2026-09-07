import type { SimilarPostModel } from '@/model/similar-post-model';
import { authenticatedFetch } from './api-service';

export async function getSimilar(postId: string): Promise<SimilarPostModel[]> {
  const params = new URLSearchParams({
    postId,
  });
  const results = await authenticatedFetch('/similarity', {
    params
  });
  return await results.json();
}
