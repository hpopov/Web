import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Credentials } from '../shared/user/user.data';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {    
  // model: any = {};
 
  credentials: Credentials = {
    username:'',
    password:''
  };
  constructor(
      private route: ActivatedRoute,
      private router: Router,
      private authService: AuthService
  ) { }

  ngOnInit() {
      // sessionStorage.setItem('token', '');
  }

  login() {
    this.authService.logIn(this.credentials, (res) => this.router.navigate(['uuu']),
     (err) => alert("Authentication failed."));
  }
}