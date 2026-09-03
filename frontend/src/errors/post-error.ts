import AppError from './app-error';

export default class PostError extends AppError {
  constructor(message: string) {
    super(message);
    this.name = 'PostError';
  }
}
