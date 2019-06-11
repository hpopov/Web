import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, of, throwError } from 'rxjs';
import { HttpParamsOptions } from '@angular/common/http/src/params';

@Injectable({
  providedIn: 'root'
})
export class WebRequestService {

  private url: string = 'https://localhost:8443/';
  private headersObj = {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  };
  constructor(private http: HttpClient) { }

  public post<T>(relativeUrl: string, body: Object,
    successfulCallback: (result: T) => void, errorCallback: (error: any) => void): Observable<T> {
    let httpOptions = {
      headers: new HttpHeaders(this.headersObj)
    };
    let result: Observable<T> = this.http.post<T>(this.url + relativeUrl, body, httpOptions);
    this.fullySubscribe(result, successfulCallback, errorCallback);
    return result;
  }

  fullySubscribe<T>(observable: Observable<T>, successfulCallback: (result:T) => void,
   errorCallback: (error:any) => void) {
    observable
      .pipe(
        catchError(this.handleError<T>())
      )
      .subscribe(response => {
        if (response) {
          // console.log("Response :" + response);
          return successfulCallback && successfulCallback(response);
        } else {
          return errorCallback && errorCallback(response);
        }
      }, error => {
        // console.log("Error :" + error);
        return errorCallback && errorCallback(error);
      })
  }

  public get<T>(relativeUrl: string, params: Object,
    successfulCallback?: (result:T) => void, errorCallback?: (any) => void): Observable<T> {
      let httpParams: HttpParams = new HttpParams();
      console.log(params);
      for(let key in params) {
        httpParams = httpParams.append(key, params[key]);
        console.log(key + ":" + params[key])
      }
      console.log(httpParams);
    let httpOptions = {
      headers: new HttpHeaders(this.headersObj),
      params: httpParams
    };
    let result: Observable<T> = this.http.get<T>(this.url + relativeUrl, httpOptions);
    this.fullySubscribe(result, successfulCallback, errorCallback);
    return result;
  }

  private handleError<T>() {
    return (error: any): Observable<T> => {
      console.error(error.error);
      return throwError(error.error);
    };
  }
}
