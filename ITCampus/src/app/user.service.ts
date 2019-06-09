import { Injectable } from '@angular/core';
import { UserData } from './user.data';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _currentUser: UserData = {
    id: 0,
    name: 'Hryhorii',
    surname: 'Popov',
    login: 'tordek'
  };
  constructor() { }
  get currentUser(): UserData {
    return this._currentUser;
  }

  public getUserByLogin(login: string): UserData {
    if (login === 'tordek') {
      return this.currentUser;
    } else {
      return null;
    }
  }
}
