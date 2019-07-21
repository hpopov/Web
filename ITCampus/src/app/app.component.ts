import { Component, OnInit } from '@angular/core';
import { PageDataService } from './page-web.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit{

  constructor(private pageDataService: PageDataService){}

  ngOnInit(): void {
    // this.router.navigate(['/login']);
  }  
}
