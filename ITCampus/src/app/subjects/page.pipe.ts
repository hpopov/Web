import { Pipe, PipeTransform } from '@angular/core';
import { SubjectData } from './subject.data';
import { NumberValueAccessor } from '@angular/forms/src/directives';

@Pipe({
  name: 'page'
})
export class PagePipe implements PipeTransform {

  transform(subjects: SubjectData[], pageNumber: number, pageMaxSize: number): SubjectData[] {
    let begin : number = Math.min(pageMaxSize*(pageNumber-1), subjects.length-1);
    let end : number = Math.min(begin + pageMaxSize, subjects.length);
    return subjects.slice(begin, end);
  }
}