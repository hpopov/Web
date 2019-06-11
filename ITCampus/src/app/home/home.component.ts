import { Component, ViewEncapsulation, OnInit, Input, AfterContentInit, AfterContentChecked, AfterViewChecked, AfterViewInit } from '@angular/core';
import { UserData } from '../user.data';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class HomeComponent implements OnInit, AfterViewInit {

  public currentUser: UserData;
  public user: UserData;
  public isLoaded: boolean = false;
  constructor(private userService: UserService, public authService: AuthService) {
    this.currentUser = {
      id : -1,
      login : 'x',
      name : 'x',
      surname : 'x',
    };
    this.user = {
      id : -1,
      login : 'x',
      name : 'x',
      surname : 'x',
    };
  }

  ngOnInit(): void {
    this.userService.getUserByLogin().subscribe(recievedUser => this.onRecieveUser(recievedUser));
    if (this.authService.hasToken()) {
      this.userService.currentUserEmitter
        .subscribe(recievedCurrentUser => this.currentUser = recievedCurrentUser);
    }
  }

  ngAfterViewInit(): void {
    this.userService.loadCurrentUser();
  }
  
  ngAfterContentInit(): void {
    // this.userService.loadCurrentUser();
  }


  private onRecieveUser(recievedUser: UserData): void {
    this.user = recievedUser;
    this.isLoaded = true;
  }

}
