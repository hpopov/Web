import { Injectable } from '@angular/core';
import { UserData } from './user.data';
import { UserService } from './user.service';
import { CleanableSubject } from 'src/app/utils/cleanable-subject';
import { AbstractResolver } from 'src/app/abstract/abstract-resolver.service';

@Injectable({
  providedIn: 'root'
})
export class CurrentUserResolver extends AbstractResolver<UserData> {

  constructor(private userService: UserService) {
    super();
  }

  protected getValueAsSubject(): CleanableSubject<UserData> {
    return this.userService.getCurrentUser();
  }
  
}
