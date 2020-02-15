import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfileService } from '../profile/profile.service';
import { CleanableSubject } from '../utils/cleanable-subject';
import { SubjectData } from './subject.data';

@Injectable({
  providedIn: 'root'
})
export class SubjectsPaginationService {

  allSubjectsSnapshot: SubjectData[];
  allSubjects: CleanableSubject<SubjectData[]>;
  // private allSubjectsLoaded: boolean = false;
  constructor(private pageDataService: ProfileService) {
    this.allSubjects = new CleanableSubject();
    this.allSubjectsSnapshot = [];
    this.bindAllSubjectsToProfile();

  }

  private bindAllSubjectsToProfile() : void {
    // this.allSubjectsLoaded = true;
    this.pageDataService.profile.asObservable()
        .pipe(map(pageData => pageData.subjects)).subscribe(subjects => {
          this.allSubjectsSnapshot = subjects.slice();
          this.allSubjects.next(this.allSubjectsSnapshot);
        });
  }

  // public getAllSubjectsAsObservable() : Observable<SubjectData[]> {
  //   if (!this.allSubjectsLoaded) {
  //     this.bindAllSubjectsToProfile();
  //   }
  //   return this.allSubjects.asObservable();
  // }

  getAllTeachersAsObservable() : Observable<string[]> {
    return this.allSubjects.asObservable().pipe(map(
      subjects => subjects.map(subject => subject.teacher)
      .filter((element, index, array) => array.indexOf(element) === index)
      ));
  }

  calculateAverageMark(subjects: SubjectData[]): number {
    let total: number = subjects.reduce((sum, current) => sum + current.mark, 0);
    return total/subjects.length;
  }

  getReachablePagesInfo(currentPageNumber: number, pageMaxSize: number,
      subjects: SubjectData[]): PageInfo[] {
    let pagesNumber: number = Math.floor(subjects.length / pageMaxSize);
    if (subjects.length % pageMaxSize !== 0 || pagesNumber === 0){
      pagesNumber++;
    }
    let reachablePagesInfo: PageInfo[] = [{pageNumber: 1, presentation: '«'}];
    this.pushPageInfoToArrayIfIsReachable(pagesNumber, reachablePagesInfo, currentPageNumber-1, '‹');
    for( let i:number = currentPageNumber-1; i<= currentPageNumber+1;i++){
      this.pushPageInfoToArrayIfIsReachable(pagesNumber, reachablePagesInfo, i);
    }
    this.pushPageInfoToArrayIfIsReachable(pagesNumber, reachablePagesInfo, currentPageNumber+1, '›');
    reachablePagesInfo.push({pageNumber: pagesNumber, presentation: '»'});
    return reachablePagesInfo;
  }

  private pushPageInfoToArrayIfIsReachable(pagesCount: number, array: PageInfo[],
    pageNumber: number, presentation: string = pageNumber.toString()) {
    if (pageNumber >= 1 && pageNumber <= pagesCount) {
      array.push({pageNumber: pageNumber, presentation: presentation});
    }
  }
}

export interface PageInfo {
  pageNumber: number;
  presentation: string;
}