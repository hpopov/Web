import { Injectable } from '@angular/core';
import { UserData } from './user.data';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _currentUser: UserData = {
    id: 0,
    name: 'Hryhorii',
    surname: 'Popov'
  };
  constructor() { }
  get currentUser(): UserData {
    return this._currentUser;
  }
}
