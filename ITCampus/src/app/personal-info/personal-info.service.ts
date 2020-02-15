import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { ProfileService } from '../profile/profile.service';
import { CleanableSubject } from '../utils/cleanable-subject';
import { WebRequestService } from '../web-request.service';
import { PersonalInfoData } from './personal-info.data';

@Injectable({
  providedIn: 'root',
})
export class PersonalInfoService {

  personalInfo: CleanableSubject<PersonalInfoData>;

  constructor(private profileService: ProfileService,
    private webRequestService: WebRequestService) {
    this.personalInfo = new CleanableSubject();
    this.bindPersonalInfoToProfile();
  }

  private bindPersonalInfoToProfile(): void {
    this.profileService.profile.asObservable().pipe(map(profile => profile.personalInfo))
      .subscribe(personalInfo => {
        this.personalInfo.next(personalInfo);
      });
  }

  // private getPersonalInfo() : CleanableSubject<PersonalInfoData> {
  //   return this.personalInfoSubject;
  // }

  // public getPersonalInfoAsObservable(): Observable<PersonalInfoData> {
  //   return this.personalInfoSubject.asObservable();
  // }

  public updatePersonalInfo(personalInfo: PersonalInfoData): void {
    this.webRequestService.put<PersonalInfoData>("/rest/personalInfos/" + personalInfo.user.login, personalInfo)
      .subscribe(response => {console.log("personalInfo updated!");this.personalInfo.next(response)});
  }

  // replaceUser(user: PublicUserData): void {//TODO: Do you really certain that you are going to need all the stuff with 'replacing'?
  // const oldPersonalInfo: PersonalInfoData = this.personalInfo.getValue();
  //   this.profileService.replacePersonalInfo({
  //     city: oldPersonalInfo.city,
  //     dateOfBirth: oldPersonalInfo.dateOfBirth,
  //     education: oldPersonalInfo.education,
  //     faculty: oldPersonalInfo.faculty,
  //     firstEducationYear: oldPersonalInfo.firstEducationYear,
  //     languages: oldPersonalInfo.languages,
  //     lastEducationYear: oldPersonalInfo.lastEducationYear,
  //     phoneNumber: oldPersonalInfo.phoneNumber,
  //     skills: oldPersonalInfo.skills,
  //     user: user
  //   })
  // }
}