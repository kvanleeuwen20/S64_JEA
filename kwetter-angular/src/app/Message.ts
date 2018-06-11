import {User} from './User';
import {HashTag} from './HashTag';

export class Message {
  id: number;
  content: string;
  hashTags: HashTag[];
  likes: User[];
  mentions: User[];
  poster: User;
  postTime: Date;
}
