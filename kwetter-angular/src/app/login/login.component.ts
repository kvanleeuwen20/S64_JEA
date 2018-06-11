import { Component, OnInit } from '@angular/core';
import {SessionService} from '../session.service';
import {LoginCredentials} from '../LoginCredentials';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: LoginCredentials;

  constructor(
    private router: Router,
    public sessionService: SessionService
  ) { }

  ngOnInit() {
    if (this.sessionService.loggedInUser) {
      this.router.navigate(['/timeline', this.sessionService.loggedInUser.id]);
    } else {
      this.model = new LoginCredentials();
    }
  }

  login() {
    this.sessionService.login(this.model.email, this.model.password)
      .subscribe(user => this.handleLoginResponse(user));
  }

  private handleLoginResponse(user) {
    this.router.navigate(['/timeline', user.id]);
  }
}
