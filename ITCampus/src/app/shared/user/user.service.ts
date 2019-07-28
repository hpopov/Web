import { Injectable } from '@angular/core';
import { UserData } from './user.data';
import { WebRequestService } from '../../web-request.service';
import { Observable } from 'rxjs';
import { ProfileService } from '../../profile/profile.service';
import { map, filter } from 'rxjs/operators';
import { TokenService } from '../token/token.service';
import { CleanableSubject } from 'src/app/utils/cleanable-subject';

@Injectable(
  {
  providedIn: 'root'
}
)
export class UserService {

  // private login: string = 'tordek';

  private currentUserSubject: CleanableSubject<UserData>;
  private currentUserLoaded: boolean = false;
  //  = {
  //   id: -1,
  //   name: '',
  //   surname: '',
  //   login: '',
  //   authorities: []
  // };
  
  private userSubject: CleanableSubject<UserData>;
  private userLoaded: boolean = false;
  

  constructor(private webRequestService: WebRequestService,
       private profileService: ProfileService, private tokenService: TokenService) { 
    this.currentUserSubject = new CleanableSubject<UserData>();
    this.userSubject = new CleanableSubject<UserData>();
  }

  private bindUserToProfile() : void {
    this.userLoaded = true;
    this.profileService.getProfileAsObservable()
      .pipe(map(profile => profile.user)).subscribe(user => {
        this.onUserChanged(user);
    });
  }

  // public loadUser(login: string) {
    
  // }

  public onUserChanged(user : UserData) {
    this.userSubject.next(user);
    console.log("Recieved user: "+ user);
  }

  public getUser(): CleanableSubject<UserData> {
    if (!this.userLoaded) {
      this.bindUserToProfile();
    }
    return this.userSubject;
  }

  public getUserAsObservable() : Observable<UserData> {
    return this.getUser().asObservable();
  }

  public loadCurrentUser():void {
    if (this.isCurrentUserLoadingAllowed()) {
      this.currentUserLoaded = true;
      this.currentUserSubject.cleanValue();
      this.webRequestService.get<UserData>("current-user", {},
        (loadedCurrentUser) => this.onCurrentUserChanged(loadedCurrentUser));
    }
  }

  private isCurrentUserLoadingAllowed(): boolean {
    return this.tokenService.hasToken();
  }

  private onCurrentUserChanged(loadedCurrentUser: UserData) : void {
    console.log("onCurrentUserChanged called :" + loadedCurrentUser);
    this.currentUserSubject.next(loadedCurrentUser);
  }

  public getCurrentUser() : CleanableSubject<UserData> {
    if (!this.currentUserLoaded) {
      this.loadCurrentUser();
    }
    return this.currentUserSubject;
  }

  public getCurrentUserAsObservable() : Observable<UserData> {
    return this.getCurrentUser().asObservable();
  }

  public removeCurrentUser():void {
    this.onCurrentUserChanged(null);
  }

  updateUser(user: UserData): Observable<boolean> {
    return this.webRequestService.post<boolean>("updateUser", user, success => {
      console.log("Sent updateUser successfully? " + success);
      if (success && this.getCurrentUser().getValue() 
          && this.getCurrentUser().getValue().id === user.id) {
        this.onCurrentUserChanged(user)
      }
      if (success && this.getUser().getValue()
          && this.getUser().getValue().id === user.id) {
        this.onUserChanged(user);
      }
    });
  }

}