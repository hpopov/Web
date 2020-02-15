import { Component, OnDestroy, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { PublicUserData, UserData } from '../shared/user/user.data';
import { UserService } from '../shared/user/user.service';
import { ProfileData } from './profile.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ProfileComponent implements OnInit, OnDestroy {

  public profile: ProfileData;
  public authenticatedUser: UserData;
  private currentUserSubscription: Subscription;
  private profileSubscription: Subscription;
  constructor(private route: ActivatedRoute, private userService: UserService) {
    console.log("Constructed ProfileComponent!");
  }

  ngOnInit(): void {
    console.log("Initializing ProfileComponent...");
    this.currentUserSubscription = this.userService.authenticatedUser.subscribe(authenticatedUser => {
      this.authenticatedUser = authenticatedUser;
    });
    this.profile = this.route.snapshot.data['profile'];
    this.profileSubscription = this.route.data.subscribe(data => {
      this.profile = data.profile;
    });
   }

  ngOnDestroy(): void {
    this.profileSubscription.unsubscribe();
    this.currentUserSubscription.unsubscribe();
  }

  public hasUserFullProfileAccess() : boolean {
    return this.userService.hasUserFullProfileAccess();
  }

}
