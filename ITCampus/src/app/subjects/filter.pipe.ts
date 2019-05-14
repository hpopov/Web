import { Pipe, PipeTransform } from '@angular/core';
import { SubjectData, SearchableSubject } from './subject.data';

@Pipe({
  name: 'filter',
  // pure: false -- decide, which approach is a better one.
})
export class FilterPipe implements PipeTransform {

  transform(subjects: SubjectData[], searchableSubject: SearchableSubject): SubjectData[] {
    return subjects.filter(subject =>
      subject.name.includes(searchableSubject.name) &&
      (searchableSubject.teacher == undefined || subject.teacher === searchableSubject.teacher) &&
      (searchableSubject.testType == undefined || searchableSubject.testType === subject.testType)
    );
  }

}
