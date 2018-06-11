import { Component, OnInit } from '@angular/core';
import {Message} from '../Message';
import {MessagesService} from '../messages.service';
import {SessionService} from '../session.service';
import {User} from '../User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-post-message',
  templateUrl: './post-message.component.html',
  styleUrls: ['./post-message.component.css']
})
export class PostMessageComponent implements OnInit {

  message: Message;

  constructor(
    private messagesService: MessagesService,
    public sessionService: SessionService,
    private router: Router
  ) {
    this.message = new Message();
    this.message.content = '';
  }


  ngOnInit() {
    if (!this.sessionService.loggedInUser) {
      this.router.navigate(['/login']);
    }
  }

  onSubmit(): void {
    this.message.id = -1;
    this.message.poster = this.sessionService.loggedInUser;
    this.message.postTime = new Date();

    this.messagesService.addMessage(this.message)
      .subscribe(result => console.log(result));
  }

}
