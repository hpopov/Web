import { Component, OnInit } from '@angular/core';
import { User } from '../user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor() { }
  user: User = {
    id: 0,
    name: 'Hryhorii',
    surname: 'Popov'
  };
  
  ngOnInit() {
  }

}
