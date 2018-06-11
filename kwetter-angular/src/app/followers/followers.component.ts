import { Component, OnInit } from '@angular/core';
import {Follower} from '../Follower';
import {UsersService} from '../users.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SessionService} from '../session.service';

@Component({
  selector: 'app-followers',
  templateUrl: './followers.component.html',
  styleUrls: ['./followers.component.css']
})
export class FollowersComponent implements OnInit {
  followers: Follower[];

  constructor(
    public sessionService: SessionService,
    private usersService: UsersService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    if (!this.sessionService.loggedInUser) {
      this.router.navigate(['/login']);
    }

    this.route.params.subscribe(params => {
      this.getFollowers();
    });
  }

  getFollowers(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.usersService.getUser(id)
      .subscribe(user => this.followers = user.followers);
  }

  navigate(followerID): void {
    this.router.navigate(['/profile/' + followerID]);
  }
}
