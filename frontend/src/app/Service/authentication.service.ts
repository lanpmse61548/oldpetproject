
import {throwError as observableThrowError} from 'rxjs';

import {catchError, map} from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';



import {RequestOptions} from '@angular/http';

@Injectable()
export class AuthenticationService {
    private authUrl = 'http://localhost:8080/petproject/login';
    private headers = new Headers({'Content-Type': 'application/json'});
 
    constructor(private http: Http) {
    }
 
   login(username: string, password: string): Observable<boolean> {
    let headers = new Headers();
    /** No need to include Content-Type in Angular 4 */
    headers.append('Accept', 'application/json');
    let options = new RequestOptions({ headers: headers });
    let body: string = JSON.stringify({username: username, password: password})
    
        return this.http.post(this.authUrl, body, options ).pipe(
            map((response: Response) => {
              //  alert( response.headers.get('Authorization'));
               let hd =response.headers.get('Authorization');
               let token = hd.split(" ")[1];
                console.log("Authorization header: " + token);
                console.log("all headers: " + response.headers.keys());
                // login successful if there's a jwt token in the response
                // let token = response.json() && response.json().token;
                localStorage.setItem('currentUser', token);
                // if (token) {
                //     // store username and jwt token in local storage to keep user logged in between page refreshes
                //     localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));
 
                //     // return true to indicate successful login
                //     return true;
                // } else {
                //     // return false to indicate failed login
                //     return false;
                // }
                return true;
            }),catchError((error:any) => observableThrowError(error.json().error || 'Server error')),);
    }
 
    getToken(): string {
      let token = localStorage.getItem('currentUser');
     // var token = currentUser && currentUser.token;
   
      return token;// ? token : "";
    }
 
    logout(): void {
        // clear token remove user from local storage to log user out
        console.log('log out');
        localStorage.removeItem('currentUser');
    }
}