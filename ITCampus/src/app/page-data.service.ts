import { Injectable } from '@angular/core';
import { WebRequestService } from './web-request.service';
import { PageData } from './home/page.data';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PageDataService {

  private pageDataObservable: Observable<PageData>;
  constructor(private webRequestService: WebRequestService) {
    console.log("In PageDataService constructor!");
    this.pageDataObservable = webRequestService.get<PageData>("pageData", {username: "tordek"});
    this.pageDataObservable.subscribe(response => console.log(response));
   }

  public getObservablePageData(): Observable<PageData> {
    console.log("Getting observable page!");
    return this.pageDataObservable;
  }
}
