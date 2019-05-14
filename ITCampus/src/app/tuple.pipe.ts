import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tuple'
})
export class TuplePipe implements PipeTransform {

  transform(tuple: [any,any], delimiter: string = ' '): string {
    return tuple[0] + delimiter + tuple[1];
  }

}
