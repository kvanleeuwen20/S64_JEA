///<reference path="../../../node_modules/rxjs/Observable.d.ts"/>
import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../User';
import {UsersService} from '../users.service';
import { Location } from '@angular/common';
import {SessionService} from '../session.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  @Input() user: User;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private usersService: UsersService,
    public sessionService: SessionService,
    private location: Location
  ) {
    route.params.subscribe(params => console.log('side menu id parameter', params['id']));
  }

  ngOnInit(): void {
    if (!this.sessionService.loggedInUser) {
      this.router.navigate(['/login']);
    }

    this.route.params.subscribe(params => {
      this.getUser();
    });
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.usersService.getUser(id)
      .subscribe(user => this.user = user);
  }

  isLoggedInUser(): boolean {
    return this.user.id === this.sessionService.loggedInUser.id;
  }

  goBack(): void {
    this.location.back();
  }

  navigate(path): void {
    this.router.navigate([{outlets: {secondary: path + '/' + this.user.id}}]);
  }

  alreadyFollowing(): boolean {
    return this.sessionService.loggedInUser.following.filter(
      f => f.id === this.user.id
    ).length > 0;
  }

  follow(): void {
    this.usersService.addFollowing(this.sessionService.loggedInUser.id, this.user.id)
      .subscribe(succeeded => this.sessionService.loggedInUser.following.push(this.user));
  }

  unfollow(): void {
    this.usersService.removeFollowing(this.sessionService.loggedInUser.id, this.user.id)
      .subscribe(succeeded => this.sessionService.loggedInUser.following = this.sessionService.loggedInUser.following
        .filter(following => following.id !== this.user.id));
  }
}
