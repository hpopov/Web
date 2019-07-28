import { Component, OnInit } from '@angular/core';
import { ProfileService } from './profile/profile.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit{

  constructor(private pageDataService: ProfileService, private router: Router){}

  ngOnInit(): void {
    // this.router.navigate(['/login']);
  }  
}
