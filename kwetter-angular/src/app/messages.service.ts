import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {catchError, tap} from 'rxjs/operators';
import {User} from './User';
import {Message} from './Message';
import {of} from 'rxjs/observable/of';
import {MessagepushendpointService} from "./messagepushendpoint.service";

@Injectable()
export class MessagesService  {
  private messagesURL = 'http://localhost:8080/Kwetter-1.0-SNAPSHOT/api/messages';
  private tokenKey = 'app_token';

  constructor(
    private http: HttpClient,
    private messagePushEndpointService: MessagepushendpointService) {}

  getMessages (id: number): Observable<Message[]> {
    const url = `${this.messagesURL}/${id}`;
    return this.http.get<Message[]>(url, this.getHTTPOptions())
      .pipe(
        tap(messages => this.log(`fetched messages`)),
        catchError(this.handleError('getMessages', []))
      );
  }

  subscribeToRealTimeMessages(): Observable<Message> {
    return this.messagePushEndpointService.subscribeToMessages();
  }

  getTimeLine (id: number): Observable<Message[]> {
    const url = `${this.messagesURL}/timeline/${id}`;
    return this.http.get<Message[]>(url, this.getHTTPOptions())
      .pipe(
        tap(messages => this.log(`fetched messages`)),
        catchError(this.handleError('getMessages', []))
      );
  }

  // addMessage (message: Message): Observable<Message> {
  //   return this.http.post<Message>(this.messagesURL + '/post', message, this.getHTTPOptions()).pipe(
  //     tap(_ => this.log(`added message w/ id=${message.id}`)),
  //     catchError(this.handleError<Message>('addMessage'))
  //   );
  // }

  addMessage (message: Message): Observable<Message> {
      this.messagePushEndpointService.addMessage(message);
      return this.messagePushEndpointService.subscribeToMessages();
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private getHTTPOptions(): object {
    return {
      headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + this.retrieve()})
    };
  }

  private retrieve() {
    const storedToken: string = localStorage.getItem(this.tokenKey);
    if (!storedToken) { throw new Error('no token found'); }
    return storedToken;
  }

  private log(message: string): void {
    console.log(message);
  }
}
