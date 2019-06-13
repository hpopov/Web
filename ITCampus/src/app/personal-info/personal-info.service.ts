import { Injectable, EventEmitter } from '@angular/core';
import { PersonalInfoData } from './personal-info.data';
import { LanguageLevelsService } from './language-levels.service';
import { PageDataService } from '../page-data.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { WebRequestService } from '../web-request.service';

@Injectable({
  providedIn: 'root',
})
export class PersonalInfoService {
  private personalInfoObservable: Observable<PersonalInfoData>;
  constructor(private pageDataService: PageDataService,
    private webRequestService: WebRequestService) {
    this.personalInfoObservable = pageDataService.getObservablePageData()
      .pipe(map(pageData => pageData.personalInfo));
  }

  getPersonalInfoObservable() : Observable<PersonalInfoData> {
    return this.personalInfoObservable;
  }
  
  updatePersonalInfo(personalInfo: PersonalInfoData, userId: number) : Observable<boolean>{
    return this.webRequestService.post<boolean>("updatePersonalInfo",
      {userId: userId, personalInfo: personalInfo});
  }
}
