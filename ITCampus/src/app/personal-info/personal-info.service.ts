import { Injectable, EventEmitter } from '@angular/core';
import { PersonalInfoData } from './personal-info.data';
import { LanguageLevelsService } from './language-levels.service';

@Injectable({
  providedIn: 'root',
})
export class PersonalInfoService {
  private lastPersonalInfo: PersonalInfoData;
  private lastUserId: number = 0;
  onUpdateEmitter: EventEmitter<void>;
  constructor(private languageLevelsService: LanguageLevelsService) { 
    this.lastPersonalInfo  = {
      phoneNumber : '(+38) 093-854-55-64',
      dateOfBirth : new Date(Date.parse('1999-03-25')),
      city : 'Kyiv',
      education : 'NTUU \"Igor Sikorsky Kyiv Polytechnic Institute\"',//{abbr:'NTUU', otherName:'\"Igor Sikorsky Kyiv Polytechnic Institute\"'},
      faculty : 'Institute of Applied System Analysis',
      educationYear : [2016, 2020],
      skills : [['Java', 'Intermediate'],
                ['Java Script', 'Background'],
                ['Paint', 'Confident']],
      languages : [['English', this.languageLevelsService.getValue('B2')],
                  ['Ukrainian', this.languageLevelsService.getValue('')],
                  ['Russian', this.languageLevelsService.getValue('')]]
    };
    this.onUpdateEmitter = new EventEmitter<void>();
  }
  getPersonalInfoByUserId(userId: number) : PersonalInfoData{
    if (this.lastUserId === userId) {
      return this.lastPersonalInfo;
    }
    return undefined;
  }
}
