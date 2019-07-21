import { Injectable } from '@angular/core';
import { WebRequestService } from './web-request.service';
import { PageData } from './home/page.data';
import { Observable, Subject, Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PageDataService {

  private pageDataObservable: Observable<PageData>;
  private pageDataSubject: Subject<PageData>;
  private subscription: Subscription;
  constructor(private webRequestService: WebRequestService) {
    console.log("In PageDataService constructor!");
    this.pageDataSubject = new Subject<PageData>();
   }

  public getObservablePageData(): Observable<PageData> {
    console.log("Getting observable page!");
    return this.pageDataSubject;
  }

  public loadObservablePageData(): void {
    
    this.pageDataObservable = this.webRequestService.get<PageData>("pageData", {username: "tordek"});
    this.subscription = this.pageDataObservable.subscribe(response => {
      console.log(response);
      this.pageDataSubject.next(response);
    });
  }
}
