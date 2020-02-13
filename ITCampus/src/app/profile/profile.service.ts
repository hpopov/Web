import { Injectable } from '@angular/core';
import { CleanableSubject } from '../utils/cleanable-subject';
import { WebRequestService } from '../web-request.service';
import { ProfileData } from './profile.model';
import { PersonalInfoData } from '../personal-info/personal-info.data';

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

  public loadProfile(): void {
    console.log("Loading observable profile...");
    // this.profile.cleanValue();
    this.webRequestService.get<ProfileData>("rest/profiles/tordek").subscribe(profile => {
          console.log(profile);
          this.profile.next(profile);
    });
  }

  public replacePersonalInfo(personalInfo: PersonalInfoData): void {
    console.log("Replacing personalInfo within a profile...");
    const previousProfile: ProfileData = this.profile.getValue();
    this.profile.next({
      personalInfo: personalInfo,
      projects: previousProfile.projects,
      subjects: previousProfile.subjects
    });
  }
}
