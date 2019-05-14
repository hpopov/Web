import { TestType } from './test-type.enum';

export interface SearchableSubject {
    name: string;
    teacher: string;
    testType: string;
}

export interface SubjectData extends SearchableSubject {
    num: number;
    academicHours: number;
    mark: number;
}