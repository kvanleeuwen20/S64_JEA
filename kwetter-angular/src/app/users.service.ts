import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {catchError, tap} from 'rxjs/operators';
import {User} from './User';
import {of} from 'rxjs/observable/of';

@Injectable()
export class UsersService {
  private usersURL = 'http://localhost:8080/Kwetter-1.0-SNAPSHOT/api/users';
  private tokenKey = 'app_token';

  constructor(
    private http: HttpClient) {}

  /** GET users from the server */
  getUsers (): Observable<User[]> {
    return this.http.get<User[]>(this.usersURL, this.getHTTPOptions())
      .pipe(
        tap(heroes => this.log(`fetched heroes`)),
        catchError(this.handleError('getHeroes', []))
      );
  }

  /** GET user by id. Will 404 if id not found */
  getUser(id: number): Observable<User> {
    const url = `${this.usersURL}/findByID/${id}`;
    return this.http.get<User>(url, this.getHTTPOptions()).pipe(
      tap(_ => this.log(`fetched user username=${id}`)),
      catchError(this.handleError<User>(`getUser id=${id}`))
    );
  }

  //////// Save methods //////////

  /** POST: add a new user to the server */
  addUser (user: User): Observable<User> {
    return this.http.post<User>(this.usersURL, user, this.getHTTPOptions()).pipe(
      tap(_ => this.log(`added user w/ id=${user.id}`)),
      catchError(this.handleError<User>('addUser'))
    );
  }

  /** DELETE: delete the user from the server */
  deleteUser (user: User | number): Observable<User> {
    const id = typeof user === 'number' ? user : user.id;
    const url = `${this.usersURL}/${id}`;

    return this.http.delete<User>(url, this.getHTTPOptions()).pipe(
      tap(_ => this.log(`deleted user id=${id}`)),
      catchError(this.handleError<User>('deleteUser'))
    );
  }

  /** PUT: update the user on the server */
  updateUser (user: User): Observable<any> {
    console.log(this.usersURL + '/update');
    return this.http.post(this.usersURL + '/update', user, this.getHTTPOptions()).pipe(
      tap(_ => this.log(`updated user id=${user.id}`)),
      catchError(this.handleError<any>('updateUser'))
    );
  }

  /** PUT: update the user on the server */
  addFollowing (userID: number, userIDFollowing: number): Observable<any> {
    const url = this.usersURL + `/followOther/${userID}/${userIDFollowing}`;
    return this.http.get(url, this.getHTTPOptions()).pipe(
      catchError(this.handleError<any>('addFollowing'))
    );
  }

  /** PUT: update the user on the server */
  removeFollowing (userID: number, userIDFollowing: number): Observable<any> {
    const url = this.usersURL + `/unfollowOther/${userID}/${userIDFollowing}`;
    return this.http.get(url, this.getHTTPOptions()).pipe(
      catchError(this.handleError<any>('removeFollowing'))
    );
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

  private log(message: string): void {
    console.log(message);
  }

  private retrieve() {
    const storedToken: string = localStorage.getItem(this.tokenKey);
    if (!storedToken) { throw new Error('no token found'); }
    return storedToken;
  }
}
