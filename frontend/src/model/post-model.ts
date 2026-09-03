import type Pet from './pet';

export default interface PostModel {
  id: string;
  title: string;
  description: string;
  imageId: string;
  status: string;
  pet: Pet;
}
