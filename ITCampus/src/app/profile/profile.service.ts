import { Injectable } from '@angular/core';
import { WebRequestService } from '../web-request.service';
import { ProfileModel } from './profile.model';
import { Observable, Subject, Subscription, BehaviorSubject } from 'rxjs';
import { map, filter } from 'rxjs/operators';
import { CleanableSubject } from '../utils/cleanable-subject';

@Injectable(
  {
  providedIn: 'root'
}
)
export class ProfileService {
  
  private profileSubject: CleanableSubject<ProfileModel>;
  private loaded: boolean;

  constructor(private webRequestService: WebRequestService) {
    console.log("In profileService constructor!");
    this.profileSubject = new CleanableSubject();
    this.loaded = false;
  }

  public getProfile(): CleanableSubject<ProfileModel> {
    console.log("Getting observable profile!");
    if (!this.loaded) {
      console.log("Profile is not loaded yet!");
      this.loadProfile();
    }
    return this.profileSubject;
  }

  public getProfileAsObservable() : Observable<ProfileModel> {
    return this.getProfile().asObservable();
  }

  public loadProfile(): void {
    this.loaded = true;
    console.log("Loading observable profile...");
    this.profileSubject.cleanValue();
    this.webRequestService.get<ProfileModel>("pageData", {username: "tordek"}).subscribe(profile => {
          console.log(profile);
          this.profileSubject.next(profile);
    })
  }
}
