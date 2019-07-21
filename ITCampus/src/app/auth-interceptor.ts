import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
  })
export class AuthInterceptor implements HttpInterceptor {

    constructor(private _router: Router, private authService: AuthService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<Object>> {
        let token = this.authService.getToken();
        console.log(token);
        if (token) {
            req = req.clone({
                headers: new HttpHeaders({
                  'Authorization': 'Bearer ' + token
                })
            });
        }

        return next.handle(req).pipe(catchError(err => {
            console.log("Recieved error : " + err);
            console.log("Error message: " +err.message);
            if (err.status === 401) {
                this.authService.logOut();
                if (err.error.message == "Expired or invalid JWT token") {
                    //TODO: Token refreshing
                }else {
                    //Logout from account or do some other stuff
                }
            }
            return throwError(err);
        }));
    }
}
