import { Injectable } from '@angular/core';
import { WebRequestService } from './web-request.service';
import { PageData } from './home/page.data';
import { Observable, Subject, Subscription } from 'rxjs';
import { AbstractWebService } from './abstract/abstract-web.service';

@Injectable({
  providedIn: 'root'
})
export class PageWebService extends AbstractWebService<PageData> {
  
  protected getModelObservable(): Observable<PageData> {
    throw new Error("Method not implemented.");
  }

}
