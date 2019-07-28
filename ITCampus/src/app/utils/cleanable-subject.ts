import { BehaviorSubject, Observable } from 'rxjs';
import { filter } from 'rxjs/operators';


export class CleanableSubject<T> extends BehaviorSubject<T> {
    private static ABSENT_VALUE = null;

    private static isValuePresent(value: any): boolean {
        return value != CleanableSubject.ABSENT_VALUE;
    }

    constructor() {
        super(CleanableSubject.ABSENT_VALUE);
    }

    public cleanValue(): void {
        this.next(CleanableSubject.ABSENT_VALUE);
    }

    public asObservable(): Observable<T> {
        return this.pipe(filter(CleanableSubject.isValuePresent));
    }

    public getValue(): T {
        let value: T = super.getValue();
        if (CleanableSubject.isValuePresent(value)) {
            return value;
        }
        return undefined;
    }
}