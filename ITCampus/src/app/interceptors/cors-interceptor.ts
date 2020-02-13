import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable(
//     {
//     providedIn: 'root'
//   }
  )
export class CorsInterceptor implements HttpInterceptor {

    constructor(){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<Object>> {
            console.log("CorsInterceptor works!");
            let headers : HttpHeaders = req.headers.append('Access-Control-Allow-Origin', '*');
            req = req.clone({
                headers: headers
            });

        return next.handle(req);
    }
}
