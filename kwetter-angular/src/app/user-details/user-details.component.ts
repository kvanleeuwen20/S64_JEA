import {Component, Input, OnInit} from '@angular/core';
import {UsersService} from '../users.service';
import {Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../User';
import {SessionService} from '../session.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  user = new User();

  constructor(
    public sessionService: SessionService,
    private usersService: UsersService,
    private router: Router,
    private route: ActivatedRoute,
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

  goBack(): void {
    this.location.back();
  }

  navigate(path): void {
    this.router.navigate([{outlets: {secondary: path + '/' + this.user.id}}]);
  }

  onSubmit() {
    console.log('SUBMIT DIE SHIT');
    this.usersService.updateUser(this.user).subscribe(updatedUser => console.log(updatedUser));
  }
}
