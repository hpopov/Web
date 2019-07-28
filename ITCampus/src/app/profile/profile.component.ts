import { Component, ViewEncapsulation, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Data } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserService } from '../shared/user/user.service';
import { ProfileModel } from './profile.model';
import { UserData } from '../shared/user/user.data';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ProfileComponent implements OnInit, OnDestroy {

  public profile: ProfileModel;
  public currentUser: UserData;
  private currentUserSubscription: Subscription;
  private profileSubscription: Subscription;
  constructor(private route: ActivatedRoute, private userService: UserService) {
    console.log("Constructed ProfileComponent!");
  }

  ngOnInit(): void {
    console.log("Initializing ProfileComponent...");
    this.currentUserSubscription = this.userService.getCurrentUser().subscribe(currentUser => {
      this.currentUser = currentUser;
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

  public isAdminRightsGranted() : boolean {
    return this.currentUser && this.profile.user.id === this.currentUser.id;
  }

}
