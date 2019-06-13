import { Injectable, EventEmitter } from '@angular/core';
import { UserData } from './user.data';
import { WebRequestService } from './web-request.service';
import { Observable, of } from 'rxjs';
import { PageDataService } from './page-data.service';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private login: string = 'tordek';

  private currentUserEmitter: EventEmitter<UserData>;
  // public userByLoginEmitter: EventEmitter<UserData>;

  public currentUser: UserData = {
    id: -1,
    name: '',
    surname: '',
    login: '',
    authorities: []
  };
  
  private currentUserObservable: Observable<UserData>;
  private userByLoginObservable: Observable<UserData>;
  // private userByLogin: UserData = {
  //   id: -1,
  //   name: '',
  //   surname: '',
  //   login: ''
  // };
  constructor(private webRequestService: WebRequestService,
      pageDataService: PageDataService, private router:Router) { 
    this.currentUserEmitter = new EventEmitter<UserData>();
    // this.userByLoginObservable = new EventEmitter<UserData>();
    
    this.currentUserObservable = pageDataService.getObservablePageData()
      .pipe(map(pageData => pageData.currentUser));
    this.currentUserObservable.subscribe(userData => this.currentUser = userData);
    this.userByLoginObservable = pageDataService.getObservablePageData()
      .pipe(map(pageData => pageData.user));
    // this.loadCurrentUser();
  }

  public getUserByLogin(): Observable<UserData> {
    return this.userByLoginObservable;
    // let localLoadingUserByLogin: LoadingUserByLogin = this.loadingUserByLogin;
    // if (localLoadingUserByLogin !== null && localLoadingUserByLogin.login === this.login) {
    //   return localLoadingUserByLogin.user;
    // } else {
    //   localLoadingUserByLogin = new LoadingUserByLogin(this.login,
    //     this.webRequestService.get<UserData>("user/" + this.login,
    //    {username:this.login}));
    //    this.loadingUserByLogin = localLoadingUserByLogin;
    //    return localLoadingUserByLogin.user;
    // }
  }

  public loadCurrentUser():void {
      // this.currentUserEmitter.emit(this.currentUser);
      this.currentUserObservable = this.webRequestService.get<UserData>("current-user", {},
      (loadedCurrentUser) => this.onCurrentUserChanged(loadedCurrentUser));
  }

  private onCurrentUserChanged(loadedCurrentUser: UserData) : void {
    this.currentUser = loadedCurrentUser;
    this.currentUserEmitter.emit(this.currentUser);
  }

  public removeCurrentUser():void {
    this.onCurrentUserChanged(null);
  }

  updateUser(user: UserData): Observable<boolean> {
    return this.webRequestService.post<boolean>("updateUser", user, success => {
      console.log("Sent updateUser successfully? " + success);
      console.log("CurrentUser: " + this.currentUser.id + ", new User: " + user.id);
      if (this.currentUser.id === user.id) {
        this.currentUser = user;
        this.currentUserEmitter.emit(this.currentUser);
      }
    });
  }

  subscribeToCurrentUserChanges(callback: (newCurrentUser:UserData) => void) {
    this.currentUserEmitter.subscribe(callback);
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

// class LoadingUserByLogin {
//   constructor(readonly login:string, readonly user: Observable<UserData>) {}
// }