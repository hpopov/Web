import { Component, ViewEncapsulation } from '@angular/core';
import { UserService } from './user.service';
import { UserData } from './user.data';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [UserService]
})
export class AppComponent {
  currentUser: UserData;
  constructor(private userService: UserService) {
    this.currentUser = userService.currentUser;
  }
  
}
