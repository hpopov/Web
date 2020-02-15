import { Injectable } from '@angular/core';
import { TokenService } from './shared/token/token.service';
import { AuthData, Credentials } from './shared/user/user.data';
import { UserService } from './shared/user/user.service';
import { WebRequestService } from './web-request.service';

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
      let relativeUrl = '/rest/authentication';
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
      this.webRequestService.post<AuthData>(relativeUrl, credentials).subscribe(succeed, error);
  }

  private loginSucceed (response: AuthData): void {
    this.tokenService.setToken(response.token);
    this.userService.loadAuthenticatedUser();
    console.log("Login succeed: " + response);
  }

  private loginFailed (error: any): void {
    console.log("Login failed: " + error);
  }

  public logOut() {
    this.tokenService.removeToken();
    this.userService.removeAuthenticatedUser();
  }
  
}
