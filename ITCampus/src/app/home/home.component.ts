import { Component, ViewEncapsulation, OnInit, Input, AfterContentInit, AfterContentChecked, AfterViewChecked, AfterViewInit } from '@angular/core';
import { UserData } from '../user.data';
import { AuthService } from '../auth.service';
import { PageDataService } from '../page-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  encapsulation: ViewEncapsulation.None,
  // providers: [UserService]
})
export class HomeComponent implements OnInit, AfterViewInit {

  public currentUser: UserData;
  public user: UserData;
  public isLoaded: boolean = false;
  constructor(private pageDataService: PageDataService, public authService: AuthService) {
    this.currentUser = {
      id : -1,
      login : 'x',
      name : 'x',
      surname : 'x',
      authorities: []
    };
    this.user = {
      id : -1,
      login : 'x',
      name : 'x',
      surname : 'x',
      authorities: []
    };
  }

  ngOnInit(): void {
    this.pageDataService.getObservablePageData().subscribe(pageData => {
      this.user = pageData.user;
      if (pageData.currentUser) {
        this.currentUser = pageData.currentUser;
      }
      this.isLoaded = true;
    })
  }

  ngAfterViewInit(): void {
    // this.userService.loadCurrentUser();
  }
  
  ngAfterContentInit(): void {
    // this.userService.loadCurrentUser();
  }

}
