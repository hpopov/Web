import { Component, OnInit } from '@angular/core';
import { UserData } from '../user.data';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';
import { PageDataService } from '../page-data.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router, public authService: AuthService, 
    private pageDataService: PageDataService, private userService: UserService) {

  }

  currentUser: UserData = {
    id: -1,
    login: '',
    name: '',
    surname: '',
    authorities: []
  }

  ngOnInit() {
    this.pageDataService.getObservablePageData().subscribe(pageData => {
      if (pageData.currentUser) {
        this.currentUser = pageData.currentUser;
      }
    });
    this.userService.currentUserEmitter
      .subscribe(recievedCurrentUser => this.currentUser = recievedCurrentUser);
  }

  logOut() {
    this.authService.logOut();
    this.router.navigateByUrl('/login');
  }

  logIn() {
    this.router.navigateByUrl('/login');
  }

}
