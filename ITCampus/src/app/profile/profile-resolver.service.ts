import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { ProfileData } from './profile.model';
import { ProfileService } from './profile.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileResolver implements Resolve<ProfileData>{

  constructor(private profileService: ProfileService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): ProfileData | Observable<ProfileData> | Promise<ProfileData> {
    const login: string = route.params.login;
    console.log(login);
    this.profileService.loadProfile(login);//?
    return this.profileService.profile.asObservable().pipe(take(1));
  }

}
