import { Injectable } from '@angular/core';
import {User} from './User';
import {UsersService} from './users.service';
import {Observable} from 'rxjs/Observable';
import {catchError, tap} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {of} from 'rxjs/observable/of';
import {Token} from './Token';

@Injectable()
export class SessionService {

  private authenticationURL = 'http://localhost:8080/Kwetter-1.0-SNAPSHOT/api/authentication';
  private tokenKey = 'app_token';

  loggedInUser: User;
  token: Token;

  constructor(
    private http: HttpClient,
    private usersService: UsersService
  ) { }

  private getUser(id: number): Observable<User> {
    const observableUser = this.usersService.getUser(id);
    observableUser.subscribe(user => this.loggedInUser = user);
    return observableUser;
  }

  private store(content: Object) {
    localStorage.setItem(this.tokenKey, JSON.stringify(content));
  }

  private retrieve() {
    const storedToken: string = localStorage.getItem(this.tokenKey);
    if (!storedToken) { throw new Error('no token found'); }
    return storedToken;
  }

  public login(email: string, password: string): Observable<User> {
    this.http.get<Token>(this.authenticationURL + `/${email}/${password}`)
      .pipe(
        catchError(this.handleError('login'))
      ).subscribe(token => this.handleToken(token));

    return this.getUser(1);
  }

  private handleToken(token) {
    this.token = token;
    const parsedToken = this.parseJwt(this.token.token);

    this.getUser(parsedToken.sub);

    this.store(this.token.token);
  }

  private parseJwt (token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
  }

  public retrieveToken() {
    const currentTime: number = (new Date()).getTime();
    let token = null;

    try {
      const storedToken = JSON.parse(this.retrieve());
      if (storedToken.ttl < currentTime) { throw new Error('invalid token found'); }
      token = storedToken.token;
    } catch (err) {
      console.error(err);
    }
    return token;

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

  private log(message: string): void {
    console.log(message);
  }

}
