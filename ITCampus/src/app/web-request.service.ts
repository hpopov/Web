import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WebRequestService {

  private url: string = 'https://localhost:8443/';
  private headersObj = {
    'Content-Type': 'application/json'
  };
  constructor(private http: HttpClient) { }

  public resolveUrl(relativeUrl: string): string {
    return this.url + relativeUrl;
  }
  public post<T>(relativeUrl: string, body: Object): Observable<T> {
    let httpOptions = {
      headers: new HttpHeaders(this.headersObj)
    };
    let url = this.resolveUrl(relativeUrl);
    console.log("Making POST request for " + url);
    return this.http.post<T>(url, body, httpOptions).pipe(
      catchError(this.handleError<T>())
    );
  }

  public put<T>(relativeUrl: string, body: Object): Observable<T> {
    let httpOptions = {
      headers: new HttpHeaders(this.headersObj)
    };
    let url = this.resolveUrl(relativeUrl);
    console.log("Making PUT request for " + url);
    return this.http.put<T>(url, body, httpOptions).pipe(
      catchError(this.handleError<T>())
    );
  }

  public get<T>(relativeUrl: string): Observable<T> {
    let httpOptions = {
      headers: new HttpHeaders(this.headersObj)
    };
    let url = this.resolveUrl(relativeUrl);
    console.log("Making GET request for " + url);
    return this.http.get<T>(url, httpOptions).pipe(
      catchError(this.handleError<T>())
    );
  }

  private handleError<T>() {
    return (error: any): Observable<T> => {
      console.error(error.error);
      return throwError(error.error);
    };
  }
}
