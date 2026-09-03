import type Pet from './pet';

export default interface Post {
  id: string;
  title: string;
  description: string;
  imageId: string;
  status: string;
  pet: Pet;
}
