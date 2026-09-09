import type { PostStatus } from '@/types/post-status';
import type Pet from './pet';
import type { UserModel } from './user-model';

export default interface PostModel {
  id: string;
  title: string;
  description: string;
  creationDate: string;
  imageFilename: string;
  user: UserModel;
  status: PostStatus;
  pet: Pet;
}
