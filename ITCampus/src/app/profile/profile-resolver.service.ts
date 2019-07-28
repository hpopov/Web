import { Injectable } from '@angular/core';
import { ProfileModel } from './profile.model';
import { ProfileService } from './profile.service';
import { AbstractResolver } from '../abstract/abstract-resolver.service';
import { CleanableSubject } from '../utils/cleanable-subject';

@Injectable({
  providedIn: 'root'
})
export class ProfileResolver extends AbstractResolver<ProfileModel>{

  constructor(private profileService: ProfileService) {
    super();
  }

  protected getValueAsSubject(): CleanableSubject<ProfileModel> {
    return this.profileService.getProfile();
  }

}
