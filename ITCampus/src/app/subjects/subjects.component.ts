import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { SubjectsPaginationService } from './subjects-pagination.service';
import { SubjectData, SearchableSubject } from './subject.data';
import { TestType } from './test-type.enum';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.scss'],
  providers: [SubjectsPaginationService]
})
export class SubjectsComponent implements OnInit, OnDestroy {

  @Input()subjects: SubjectData[];
  subjectSubscription: Subscription;
  teachers: String[];
  teachersSubscription: Subscription;
  @Input()
  pageMaxSize: number = 10;
  @Input()
  currentPage: number = 1;
  searchableSubject: SearchableSubject = {
    name: '',
    teacher: undefined,
    testType: undefined
  };
  testTypeValues: string[];

  constructor(public subjectsPaginationService: SubjectsPaginationService) {
    // this.subjects = [];
    this.teachers = [];
  }

  ngOnInit() {
    this.subjectSubscription = this.subjectsPaginationService.allSubjects.asObservable()
        .subscribe(subjects => {
      this.subjects = subjects;
    });
    this.teachersSubscription = this.subjectsPaginationService.getAllTeachersAsObservable()
        .subscribe(teachers => {
      this.teachers = teachers;
    });
    this.testTypeValues = [];
    for(let value in TestType) {
      this.testTypeValues.push(TestType[value]);
    }
  }

  ngOnDestroy(): void {
    this.subjectSubscription.unsubscribe();
    this.teachersSubscription.unsubscribe();
  }


  doSearch() {
    this.searchableSubject = {
      name: this.searchableSubject.name,
      teacher: this.searchableSubject.teacher,
      testType: this.searchableSubject.testType
    };
  }

}
