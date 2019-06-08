import { Component, OnInit, Input } from '@angular/core';
import { SubjectsPaginationService } from './subjects-pagination.service';
import { SubjectData, SearchableSubject } from './subject.data';
import { TestType } from './test-type.enum';

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.scss'],
  providers: [SubjectsPaginationService]
})
export class SubjectsComponent implements OnInit {

  subjects: SubjectData[];
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
    this.subjects = subjectsPaginationService.allSubjects;
    this.testTypeValues = [];
    for(let value in TestType) {
      this.testTypeValues.push(TestType[value]);
    }
  }

  ngOnInit() {
  }

  doSearch() {
    this.searchableSubject = {
      name: this.searchableSubject.name,
      teacher: this.searchableSubject.teacher,
      testType: this.searchableSubject.testType
    };
  }

}
