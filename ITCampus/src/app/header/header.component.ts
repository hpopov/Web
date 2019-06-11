import { Component, OnInit, Input } from '@angular/core';
import { UserData } from '../user.data';
import { Router } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router, public authService: AuthService, public userService: UserService) {

  }

  currentUser: UserData = {
    id: -1,
    login: '',
    name: '',
    surname: ''
  }

  ngOnInit() {
    this.userService.currentUserEmitter
      .subscribe(recievedCurrentUser => this.currentUser = recievedCurrentUser);
    this.userService.loadCurrentUser();
  }

  logOut() {
    this.authService.logOut();
    this.router.navigateByUrl('/login');
  }

  logIn() {
    this.router.navigateByUrl('/login');
  }

}
