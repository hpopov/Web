import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {    
  // model: any = {};
 
  constructor(
      private route: ActivatedRoute,
      private router: Router,
      private http: HttpClient,
      private authService: AuthService
  ) { }

  ngOnInit() {
      sessionStorage.setItem('token', '');
  }

  login(form: NgForm) {
    // this.model = {};
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Access-Control-Allow-Origin': '*'
      })
    };
      let url = 'http://localhost:8080/login';
      let result = this.http.post(url, {
          username: form.value.username,
          password: form.value.password
      }, httpOptions).subscribe(isValid => {
          if (isValid) {
              this.authService.isLoggedIn = true;
              sessionStorage.setItem(
                'token',
                btoa(form.value.username + ':' + form.value.password)
              );
              this.router.navigate(['']);
          } else {
              alert("Authentication failed.");
          }
      });
  }
}
