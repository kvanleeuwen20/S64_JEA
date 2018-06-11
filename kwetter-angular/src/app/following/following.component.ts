import { Component, OnInit } from '@angular/core';
import {UsersService} from '../users.service';
import {Follower} from '../Follower';
import {ActivatedRoute, Router} from '@angular/router';
import {SessionService} from '../session.service';

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {
  following: Follower[];

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
      .subscribe(user => this.following = user.following);
  }

  navigate(followingID): void {
    console.log('navigate');
    this.router.navigate(['/profile/' + followingID]);
  }
}
