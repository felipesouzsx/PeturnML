import type Pet from './pet';
import type { UserModel } from './user-model';

export default interface PostModel {
  id: string;
  title: string;
  description: string;
  imageFilename: string;
  user: UserModel;
  status: string;
  pet: Pet;
}
