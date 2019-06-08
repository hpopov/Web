import { Component, ViewEncapsulation } from '@angular/core';
import { UserData } from '../user.data';
import { UserService } from '../user.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class HomeComponent {
  currentUser: UserData;
  constructor(private userService: UserService) {
    this.currentUser = userService.currentUser;
  }
}
