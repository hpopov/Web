import { Injectable, EventEmitter } from '@angular/core';
import { UserData } from './user.data';
import { WebRequestService } from './web-request.service';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private login: string = 'tordek';

  public currentUserEmitter: EventEmitter<UserData>;

  public currentUser: UserData = {
    id: -1,
    name: '',
    surname: '',
    login: '',
    authorities: []
  };
  
  private loadingCurrentUser: Observable<UserData>
  private loadingUserByLogin: LoadingUserByLogin;
  // private userByLogin: UserData = {
  //   id: -1,
  //   name: '',
  //   surname: '',
  //   login: ''
  // };
  constructor(private webRequestService: WebRequestService) { 
    this.currentUserEmitter = new EventEmitter<UserData>();

    this.loadingUserByLogin = null;
    this.loadingCurrentUser = null;
    // this.loadCurrentUser();
  }
  // get currentUser(): UserData {
  //   return this._currentUser;
  // }

  public getUserByLogin(): Observable<UserData> {
    let localLoadingUserByLogin: LoadingUserByLogin = this.loadingUserByLogin;
    if (localLoadingUserByLogin !== null && localLoadingUserByLogin.login === this.login) {
      return localLoadingUserByLogin.user;
    } else {
      localLoadingUserByLogin = new LoadingUserByLogin(this.login,
        this.webRequestService.get<UserData>("user/" + this.login,
       {username:this.login}));
       this.loadingUserByLogin = localLoadingUserByLogin;
       return localLoadingUserByLogin.user;
    }
  }

  public loadCurrentUser() {
    if (this.currentUser.id > -1) {
      this.currentUserEmitter.emit(this.currentUser);
    } else {
      this.webRequestService.get<UserData>("current-user", {},
      (loadedCurrentUser) => this.onCurrentUserLoaded(loadedCurrentUser));
    }
  }

  private onCurrentUserLoaded(loadedCurrentUser: UserData) : void {
    this.currentUser = loadedCurrentUser;
    this.currentUserEmitter.emit(loadedCurrentUser)
  }
  // public getCurrentUser(): Observable<UserData> {
  //   let localLoadingCurrentUser: Observable<UserData> = this.loadingCurrentUser;
  //   if (localLoadingCurrentUser !== null) {
  //     return localLoadingCurrentUser;
  //   } else {
  //     localLoadingCurrentUser = this.webRequestService.get<UserData>("current-user", {});
  //      this.loadingCurrentUser = localLoadingCurrentUser;
  //      return localLoadingCurrentUser;
  //   }
  //   // return this.webRequestService.get<UserData>("current-user", {},
  //   // (loadedCurrentUser) => this._currentUser = loadedCurrentUser);
  // }

}

class LoadingUserByLogin {
  constructor(readonly login:string, readonly user: Observable<UserData>) {}
}