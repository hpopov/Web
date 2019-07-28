import { Injectable } from '@angular/core';
import { PersonalInfoData } from './personal-info.data';
import { ProfileService } from '../profile/profile.service';
import { map, filter } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { WebRequestService } from '../web-request.service';
import { UserService } from '../shared/user/user.service';
import { CleanableSubject } from '../utils/cleanable-subject';

@Injectable({
  providedIn: 'root',
})
export class PersonalInfoService {

  private personalInfoSubject: CleanableSubject<PersonalInfoData>;

  constructor(private profileService: ProfileService, private userService: UserService,
    private webRequestService: WebRequestService) {
    this.personalInfoSubject = new CleanableSubject();
    this.bindPersonalInfoToProfile();
  }

  private bindPersonalInfoToProfile() : void {
    this.profileService.getProfileAsObservable().pipe(map(pageData => pageData.personalInfo))
      .subscribe(personalInfo => {
        this.personalInfoSubject.next(personalInfo);
      });
  }

  // private getPersonalInfo() : CleanableSubject<PersonalInfoData> {
  //   return this.personalInfoSubject;
  // }

  public getPersonalInfoAsObservable(): Observable<PersonalInfoData> {
    return this.personalInfoSubject.asObservable();
  }
  
  public updatePersonalInfo(personalInfo: PersonalInfoData, userId: number) : void{
    this.webRequestService.post<boolean>("updatePersonalInfo",
      {userId: userId, personalInfo: personalInfo}).subscribe(succeed => {
        if (succeed && this.userService.getUser().getValue()
           && this.userService.getUser().getValue().id === userId) {
          this.personalInfoSubject.next(personalInfo);
        }
      });
  }
}