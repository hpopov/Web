import { Pipe, PipeTransform } from '@angular/core';

export class YearInterval {
	constructor (firstYear: number, lastYear:number) {
		if (firstYear > lastYear) {
			this.lastYear = firstYear;
			this.firstYear = lastYear;
		} else {
			this.firstYear = firstYear;
			this.lastYear = lastYear;
		}
	}
	readonly firstYear:number;
	readonly lastYear:number;
}

@Pipe({
	name: 'intervalPipe'
})
export class IntervalPipe implements PipeTransform{
	transform(interval: YearInterval, delimiter: string): string {
		return interval.firstYear.toString()+delimiter+interval.lastYear.toString();
	}
}