import { Component, OnInit, Input } from '@angular/core';
import { UserData } from '../user.data';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor() { }
  
  @Input()
  user: UserData;
  
  ngOnInit() {
  }

}
