import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { UserData } from './user.data';
import { Router } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit{

  constructor(public userService: UserService){}

  ngOnInit(): void {
    // this.router.navigate(['/login']);
  }  
}
