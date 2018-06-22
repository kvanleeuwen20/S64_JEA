import {Component, OnInit} from '@angular/core';
import {MessagesService} from '../messages.service';
import {Message} from '../Message';
import {ActivatedRoute, Router} from '@angular/router';
import {SessionService} from '../session.service';

@Component({
  selector: 'app-websockettest',
  templateUrl: './websockettest.component.html',
  styleUrls: ['./websockettest.component.css']
})
export class WebsockettestComponent implements OnInit {
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

    this.messagesService.subscribeToRealTimeMessages().subscribe(message => this.addNewMessage(message.data));
    //this.messagesService.subscribeToRealTimeMessages().subscribe(message => this.messages.unshift(message.data));
    this.getMessages();
  }

  addNewMessage(messageString: String) {
    let message = JSON.parse(messageString);
    console.log(typeof(message))
    console.log(message);
    this.messages.unshift(message);
  }

  getMessages(): void {
    const id = +this.route.snapshot.paramMap.get('id');

    this.messagesService.getTimeLine(id)
      .subscribe(messages => this.messages = messages);
  }
}
