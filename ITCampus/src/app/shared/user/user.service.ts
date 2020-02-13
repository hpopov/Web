import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { PersonalInfoService } from 'src/app/personal-info/personal-info.service';
import { CleanableSubject } from 'src/app/utils/cleanable-subject';
import { WebRequestService } from '../../web-request.service';
import { TokenService } from '../token/token.service';
import { PublicUserData, UserData } from './user.data';

@Injectable(
  {
    providedIn: 'root'
  }
)
export class UserService {

  authenticatedUser: CleanableSubject<UserData>;
  user: CleanableSubject<PublicUserData>;


  constructor(private webRequestService: WebRequestService,
    private personalInfoService: PersonalInfoService, private tokenService: TokenService) {
    this.authenticatedUser = new CleanableSubject<UserData>();
    this.user = new CleanableSubject<UserData>();
    this.bindUserToPersonalInfo();
  }

  private bindUserToPersonalInfo(): void {
    this.personalInfoService.personalInfo.asObservable()
      .pipe(map(personalInfo => personalInfo.user)).subscribe(user => {
        this.onUserChanged(user);
      });
  }

  public onUserChanged(user: PublicUserData) {
    this.user.next(user);
    console.log("Recieved user: " + user);
    // this.personalInfoService.replaceUser(user);
  }

  public loadAuthenticatedUser(): void {
    if (this.isUserAuthenticated()) {
      // this.authenticatedUser.cleanValue();
      this.webRequestService.get<UserData>("rest/authentication").subscribe(this.onAuthenticatedUserChanged);
    }
  }

  private isUserAuthenticated(): boolean {
    return this.tokenService.hasToken();
  }

  private onAuthenticatedUserChanged(loadedCurrentUser: UserData): void {
    console.log("onAuthenticatedUserChanged called :" + loadedCurrentUser);
    this.authenticatedUser.next(loadedCurrentUser);
  }

  public removeAuthenticatedUser(): void {
    this.onAuthenticatedUserChanged(null);
  }

  public updateUser(user: PublicUserData): void {
    this.webRequestService.put<PublicUserData>("rest/users/" + user.login, user).subscribe(this.onUserUpdated);
  }

  private onUserUpdated(user: PublicUserData): void {
    console.log("Sent updateUser successfully");
    if (this.authenticatedUser.getValue() && this.authenticatedUser.getValue().login === user.login) {
      this.onAuthenticatedUserChanged({
        authorities: this.authenticatedUser.getValue().authorities,
        id: this.authenticatedUser.getValue().id,
        login: user.login,
        name: user.name,
        surname: user.surname
      });
    }
    if (this.user.getValue().login === user.login) {
      this.onUserChanged(user);
    }
  }

  public hasUserFullProfileAccess(): boolean {
    return this.authenticatedUser.getValue() && this.authenticatedUser.getValue().login === this.user.getValue().login;
  }

  public getUserAvatarUrl(username: string): string {
    return this.webRequestService.resolveUrl("rest/users/"+username+"/avatar");
  }

}