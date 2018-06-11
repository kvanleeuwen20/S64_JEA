import {Follower} from './Follower';

export class User {
  id: number;
  username: string;
  email: string;
  website: string;
  bio: string;
  name: string;
  profilePicturePath: string;
  role: string;
  following: Follower[];
  followers: Follower[];
}
