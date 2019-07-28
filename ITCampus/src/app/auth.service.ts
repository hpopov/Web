import { Injectable } from '@angular/core';
import { Credentials, AuthData, UserData } from './shared/user/user.data';
import { WebRequestService } from './web-request.service';
import { UserService } from './shared/user/user.service';
import { Subscription } from 'rxjs';
import { TokenService } from './shared/token/token.service';

@Injectable(
//   {
//   providedIn: 'root'
// }
)
export class AuthService {
  
  constructor(private webRequestService : WebRequestService, private userService: UserService,
    private tokenService : TokenService) { }

  public isLoggedIn() : boolean {
    return this.tokenService.hasToken();
  }

  public logIn(credentials: Credentials, successfulCallback?: (AuthData)=>void,
   errorCallback?: (any)=>void) {
      let relativeUrl = 'login';
      let succeed: (AuthData)=>void = (response)=> {
        this.loginSucceed(response);
        if (successfulCallback) {
          successfulCallback(response); 
        }
      };
      let error: (any)=>void = (err)=> {
        this.loginFailed(err);
        errorCallback && errorCallback(error);
      };
      this.webRequestService.post<AuthData>(relativeUrl, credentials, succeed, error);
  }

  private loginSucceed (response: AuthData): void {
    this.tokenService.setToken(response.token);
    this.userService.loadCurrentUser();
    console.log("Login succeed: " + response);
  }

  private loginFailed (error: any): void {
    console.log("Login failed: " + error);
  }

  public logOut() {
    this.tokenService.removeToken();
    this.userService.removeCurrentUser();
  }

}
