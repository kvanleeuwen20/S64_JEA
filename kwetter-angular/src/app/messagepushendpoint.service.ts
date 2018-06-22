import { Observable, Observer, Subject } from "rxjs/Rx";
import { Injectable } from '@angular/core';
import {Message} from "./Message";
import {SessionService} from "./session.service";

@Injectable()
export class MessagepushendpointService {
  private URL = 'ws://localhost:8080/Kwetter-1.0-SNAPSHOT/messagepush/' + this.sessionService.loggedInUser.id;
  private socket: Subject<Message>;

  private connect(url): Subject<Message> {
    if(!this.socket) {
      this.socket = this.create(url);
    }
    return this.socket;
  }

  private create(url): Subject<Message> {
    let ws = new WebSocket(url);
    Observable.create()
    let observable = Observable.create(
      (obs: Observer<Message>) => {
        ws.onmessage = obs.next.bind(obs);
        ws.onerror = obs.error.bind(obs);
        ws.onclose = obs.complete.bind(obs);
        return ws.close.bind(ws);
      }
    );

    let observer = {
      next: (data: Object) => {
        if (ws.readyState === WebSocket.OPEN) {
          ws.send(JSON.stringify(data));
        }
      },
    };

    return Subject.create(observer, observable);
  }

  public addMessage(message: Message) {
    console.log(this.URL);
    return this.socket.next(message);
  }

  public subscribeToMessages() : Observable<Message> {
    console.log('subscribing!');
    return this.socket.asObservable();
  }

  constructor(private sessionService: SessionService) {
    this.connect(this.URL);
  }
}
