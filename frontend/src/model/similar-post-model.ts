import type { PostStatus } from '../types/post-status';

export interface SimilarPostModel {
  postId: string;
  postTitle: string;
  postDescription: string;
  imageId: string;
  status: PostStatus;
  similarity: number;
}
