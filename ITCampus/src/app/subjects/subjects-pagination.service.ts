import { Injectable } from '@angular/core';
import { SubjectData } from './subject.data';
import { TestType } from './test-type.enum';
import { PageDataService } from '../page-web.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SubjectsPaginationService {

  allSubjects: SubjectData[];
  allSubjectsObservable: Observable<SubjectData[]>;
  constructor(private pageDataService: PageDataService) {
    this.allSubjectsObservable = pageDataService.getObservablePageData()
        .pipe(map(pageData => pageData.subjects));
    this.allSubjects = [];
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

  getAllTeachersObservable() : Observable<string[]> {
    return this.allSubjectsObservable.pipe(map(
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