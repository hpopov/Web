import { LanguageLevelData } from './language-level.data';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
  })
export class LanguageLevelsService {
    private innerValues : Map<string,string>;
    constructor() {
        if (this.innerValues == undefined) {
            this.innerValues = new Map();
            this.innerValues.set('', 'Native');
            this.innerValues.set('C2', 'Profficient');
            this.innerValues.set('C1', 'Advanced');
            this.innerValues.set('B2', 'High Intermediate');
            this.innerValues.set('B1', 'Low Intermediate');
            this.innerValues.set('A2', 'Elementary');
        }
    }

    getValue(code: string) : string {
        return this.innerValues.get(code);
    }
    getByCode(code: string) : LanguageLevelData {
        let uppercaseCode:string = code.toUpperCase();
        if (this.innerValues.has(uppercaseCode)) {
            return {code: uppercaseCode, name: this.innerValues.get(uppercaseCode)};
        }
        return undefined;
    }

}
