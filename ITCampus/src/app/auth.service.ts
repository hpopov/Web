import { Injectable } from '@angular/core';
import { Credentials, AuthData, UserData } from './user.data';
import { WebRequestService } from './web-request.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // private loggedIn:boolean = false;
  private currentUser: UserData = {
    id: -1,
    login: '',
    name: '',
    surname: '',
    authorities: []
  };
  hasToken(): boolean {
    return localStorage.getItem('token') !== null;
  }

  isLoggedIn() : boolean {
    return this.hasToken && this.currentUser.id > -1;
  }

  constructor(private webRequestService : WebRequestService, private userService: UserService) { 
    
  }

  public logIn(credentials: Credentials, successfulCallback: (AuthData)=>void, errorCallback: (any)=>void) {
      let relativeUrl = 'login';
      let succeed: (AuthData)=>void = (response)=> {
        this.loginSucceed(response);
        return successfulCallback && successfulCallback(response);
      };
      let error: (any)=>void = (err)=> {
        this.loginFailed(err);
        return errorCallback && errorCallback(error);
      };
      this.webRequestService.post<AuthData>(relativeUrl, credentials, succeed, error);
  }

  private loginSucceed (response: AuthData): void {
    localStorage.setItem(
      'token', response.token
    );
    console.log(this);
    // this.userService.loadCurrentUser();
    // this.userService.getCurrentUser()
    //   .subscribe(recievedCurrentUser => this.currentUser = recievedCurrentUser);
    console.log("Login succeed: " + response);
  }

  private loginFailed (error: any): void {
    console.log("Login failed: " + error);
  }

  logOut() {
    localStorage.removeItem('token');
  }

  getToken() {
      return localStorage.getItem('token');
  }

}
