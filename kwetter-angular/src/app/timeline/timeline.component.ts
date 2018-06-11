import { Component, OnInit } from '@angular/core';
import {MessagesService} from '../messages.service';
import {Message} from '../Message';
import {ActivatedRoute, Router} from '@angular/router';
import {SessionService} from '../session.service';

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.css']
})
export class TimelineComponent implements OnInit {
  messages: Message[];

  constructor(
    public sessionService: SessionService,
    private messagesService: MessagesService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (!this.sessionService.loggedInUser) {
      this.router.navigate(['/login']);
    }

    this.getMessages();
  }

  getMessages(): void {
    const id = +this.route.snapshot.paramMap.get('id');

    this.messagesService.getTimeLine(id)
      .subscribe(messages => this.messages = messages);
  }
}
