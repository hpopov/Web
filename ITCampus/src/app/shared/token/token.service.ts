import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  public getToken() {
    return localStorage.getItem('token');
  }

  public hasToken(): boolean {
    return localStorage.getItem('token') !== null;
  }

  public setToken(token: string): void {
    localStorage.setItem(
      'token', token
    );
  }

  public removeToken(): void {
    localStorage.removeItem('token');
  }
}
