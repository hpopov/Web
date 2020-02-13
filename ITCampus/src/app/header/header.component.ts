import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserData } from '../shared/user/user.data';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';
import { UserService } from '../shared/user/user.service';
import { ProfileService } from '../profile/profile.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {

  private authenticatedUserSubscription: Subscription;

  constructor(private router: Router, public authService: AuthService,
     private userService: UserService, private route: ActivatedRoute) {
      console.log("Header component says, that userService is: "+userService);
  }

  authenticatedUser: UserData;
  //  = {
  //   id: -1,
  //   login: '',
  //   name: '',
  //   surname: '',
  //   authorities: []
  // }

  ngOnInit() {
    this.authenticatedUserSubscription = this.userService.authenticatedUser.subscribe(currentUser => {
        this.authenticatedUser = currentUser;
    });
  }

  ngOnDestroy(): void {
    this.authenticatedUserSubscription.unsubscribe();
  }


  logOut() {
    this.authService.logOut();
    console.log("Logging out...");
    // this.router.navigateByUrl('/login');
  }

  logIn() {
    console.log("Logging in...");
    this.router.navigate(['/login']);
  }

  getUserAvatarUrl(username: string): string {
    return this.userService.getUserAvatarUrl(username);
  }

}