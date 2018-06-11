import { Component, OnInit } from '@angular/core';
import {UsersService} from '../users.service';
import {User} from '../User';
import {MessagesService} from '../messages.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Message} from '../Message';
import {SessionService} from '../session.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  messages: Message[];

  constructor(
    public sessionService: SessionService,
    private messagesService: MessagesService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getMessages();
  }

  getMessages(): void {
    const id = +this.route.snapshot.paramMap.get('id');

    this.messagesService.getMessages(id)
      .subscribe(messages => this.messages = messages);
  }
}
