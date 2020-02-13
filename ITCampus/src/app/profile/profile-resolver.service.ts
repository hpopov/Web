import { Injectable } from '@angular/core';
import { ProfileData } from './profile.model';
import { ProfileService } from './profile.service';
import { AbstractResolver } from '../abstract/abstract-resolver.service';
import { CleanableSubject } from '../utils/cleanable-subject';

@Injectable({
  providedIn: 'root'
})
export class ProfileResolver extends AbstractResolver<ProfileData>{

  constructor(private profileService: ProfileService) {
    super();
  }

  protected getValueAsSubject(): CleanableSubject<ProfileData> {
    this.profileService.loadProfile();//?
    return this.profileService.profile;
  }

}
