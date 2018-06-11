import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from './User';
import {UsersService} from './users.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SessionService} from './session.service';
import {Token} from './Token';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Kwetter';

  constructor(
    public router: Router,
    private route: ActivatedRoute,
    public sessionService: SessionService) {  }

  ngOnInit() {
  }
}
