import { Component, OnInit, Input } from '@angular/core';
import { UserData } from '../user.data';
import { Router } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router, public authService: AuthService) { }
  
  @Input()
  user: UserData;
  
  ngOnInit() {
  }

  logOut() {
    this.authService.isLoggedIn = false;
    this.router.navigateByUrl('/login');
  }
  
  logIn() {
    this.router.navigateByUrl('/login');
  }

}
