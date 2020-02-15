import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth.service';
import { UserData } from '../shared/user/user.data';
import { UserService } from '../shared/user/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {

  private authenticatedUserSubscription: Subscription;
  userAvatarUrl: string = '';
  isUserAuthenticated: boolean = false;

  constructor(private router: Router, public authService: AuthService,
    private userService: UserService, private route: ActivatedRoute) {
    console.log("Header component says, that userService is: " + userService);
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
    this.userService.loadAuthenticatedUser();
    this.authenticatedUserSubscription = this.userService.authenticatedUser.subscribe(currentUser => {
      console.log("Header Component received authenticated user!");
      this.authenticatedUser = currentUser;
      this.isUserAuthenticated = this.authService.isLoggedIn() && !!currentUser;
      console.log(this.isUserAuthenticated);
      if (currentUser != null) {
        this.userAvatarUrl = this.userService.getUserAvatarUrl(currentUser.login);
      } else {
        this.userAvatarUrl = '';
      }
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

}