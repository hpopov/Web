import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { TokenService } from '../shared/token/token.service';

@Injectable(
//     {
//     providedIn: 'root'
//   }
  )
export class AuthInterceptor implements HttpInterceptor {

    
    constructor(private tokenService: TokenService){ }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<Object>> {
        let token = this.tokenService.getToken();
        console.log("Token: " + token);
        if (token) {
            let headers: HttpHeaders = req.headers.append('Authorization', 'Bearer ' + token);
            req = req.clone({
                headers: headers
            });
        }

        return next.handle(req).pipe(catchError(err => {
            console.log("Recieved error : " + err);
            console.log("Error message: " +err.message);
            if (err.status === 401) {
                this.tokenService.removeToken();
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
