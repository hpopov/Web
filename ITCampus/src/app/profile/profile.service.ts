import { Injectable } from '@angular/core';
import { CleanableSubject } from '../utils/cleanable-subject';
import { WebRequestService } from '../web-request.service';
import { ProfileData } from './profile.model';

@Injectable(
  {
  providedIn: 'root'
}
)
export class ProfileService {
  
  profile: CleanableSubject<ProfileData>;

  constructor(private webRequestService: WebRequestService) {
    console.log("In profileService constructor!");
    this.profile = new CleanableSubject();
  }

  public loadProfile(username: string): void {
    console.log("Loading observable profile...");
    this.profile.cleanValue();//is used to mute current value for resolver, so that it could wait for the actual new value
    this.webRequestService.get<ProfileData>("/rest/profiles/" + username).subscribe(profile => {
          // console.log(profile);
          this.profile.next(profile);
    });
  }

  // public replacePersonalInfo(personalInfo: PersonalInfoData): void {
  //   console.log("Replacing personalInfo within a profile...");
  //   const previousProfile: ProfileData = this.profile.getValue();
  //   this.profile.next({
  //     personalInfo: personalInfo,
  //     projects: previousProfile.projects,
  //     subjects: previousProfile.subjects
  //   });
  // }
}
