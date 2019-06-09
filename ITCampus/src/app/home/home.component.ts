import { Component, ViewEncapsulation } from '@angular/core';
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
export class HomeComponent {
  public user: UserData;
  constructor(private userService: UserService, public authService: AuthService) {
    this.user = userService.getUserByLogin('tordek');
  }

}
