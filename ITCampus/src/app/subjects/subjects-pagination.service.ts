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
    //   {
    //     num: 1,
    //     name: 'Math Analysis',
    //     teacher: 'Bohonov Y.E.',
    //     testType: TestType.EXAM,
    //     academicHours: 120,
    //     mark: 97
    //   },
    //   {
    //     num: 2,
    //     name: 'Discrete mathematics',
    //     teacher: 'Stus O.V.',
    //     testType: TestType.CREDIT,
    //     academicHours: 80,
    //     mark: 96
    //   },
    //   {
    //     num: 3,
    //     name: 'Linear algebra',
    //     teacher: 'Minarchenko O.M.',
    //     testType: TestType.CREDIT,
    //     academicHours: 60,
    //     mark: 97
    //   },
    //   {
    //     num: 4,
    //     name: 'Functional analysis',
    //     teacher: 'Bohonov Y.E.',
    //     testType: TestType.CREDIT,
    //     academicHours: 40,
    //     mark: 95
    //   },
    //   {
    //     num: 5,
    //     name: 'Foundation of algorithms',
    //     teacher: 'Romanov V.V.',
    //     testType: TestType.EXAM,
    //     academicHours: 80,
    //     mark: 95
    //   },
    //   {
    //     num: 6,
    //     name: 'Object Oriented Programming',
    //     teacher: 'Liashenko M.V.',
    //     testType: TestType.EXAM,
    //     academicHours: 80,
    //     mark: 95
    //   },
    //   {
    //     num: 7,
    //     name: 'Operation calculus',
    //     teacher: 'Bohonov Y.E.',
    //     testType: TestType.CREDIT,
    //     academicHours: 24,
    //     mark: 95
    //   },
    //   {
    //     num: 8,
    //     name: 'Intellectual Data Analysis',
    //     teacher: 'Ishchenko H.V.',
    //     testType: TestType.EXAM,
    //     academicHours: 60,
    //     mark: 100
    //   },
    //   {
    //     num: 9,
    //     name: 'Web technology',
    //     teacher: 'Golubova I.A.',
    //     testType: TestType.CREDIT,
    //     academicHours: 60,
    //     mark: 95
    //   },
    //   {
    //     num: 10,
    //     name: 'Operation systems',
    //     teacher: 'Beznosyk O.Y',
    //     testType: TestType.CREDIT,
    //     academicHours: 60,
    //     mark: 96
    //   },
    // ];
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
    if (subjects.length % pageMaxSize !== 0){
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