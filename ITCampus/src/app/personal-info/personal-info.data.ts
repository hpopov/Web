import { formatDate } from '@angular/common';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { PublicUserData } from '../shared/user/user.data';

export interface PersonalInfoData {
  user: PublicUserData;
  phoneNumber: string;
  dateOfBirth: Date;//?
  city: string;
  education: string
  faculty: string;
  firstEducationYear: number;
  lastEducationYear: number;
  skills: object;
  languages: object;
}

export class PersonalInfoFormGroup {
  readonly form: FormGroup = new FormGroup({
    'user': new FormGroup({
      'surname': new FormControl(null),
      'name': new FormControl(null),
    }),
    'phoneNumber': new FormControl(null),
    'dateOfBirth': new FormControl(null),
    'city': new FormControl(null),
    'education': new FormControl(null),
    'faculty': new FormControl(null),
    'educationYears': new FormArray([
      new FormControl(null),
      new FormControl(null)
    ]),
    'skills': new FormArray([]),
    'languages': new FormArray([])
  });

  constructor() {
  }

  // constructor(personalInfo: PersonalInfoData) {
  //   this.populateValue(personalInfo);
  // }

  // | date: 'dd.MM.yyyy'
  private convertDate(date: string): Date {
    if (date == null || date === '') {
      return null;
    }
    return new Date(Date.UTC(Number.parseFloat(date.slice(6, 10)), Number.parseFloat(date.slice(3, 5)) - 1,
      Number.parseFloat(date.slice(0, 2))));
  }

  public asData(username: string): PersonalInfoData {
    const data = {
      city: this.form.get('city').value,
      dateOfBirth: this.convertDate(this.form.get('dateOfBirth').value),
      education: this.form.get('education').value,
      faculty: this.form.get('faculty').value,
      firstEducationYear: (this.form.get('educationYears') as FormArray).at(0).value,
      lastEducationYear: (this.form.get('educationYears') as FormArray).at(1).value,
      phoneNumber: this.form.get('phoneNumber').value,
      languages: this.convertListToMap((this.form.get('languages') as FormArray).getRawValue()),
      skills: this.convertListToMap((this.form.get('skills') as FormArray).getRawValue()),
      user: {
        login: username,
        name: this.form.get(['user', 'name']).value,
        surname: this.form.get(['user', 'surname']).value
      }
    };
    // console.log(data);
    return data;
  }

  private convertListToMap(list: [string, string][]): object {
    const map: object = {};
    for (let tuple of list) {
      map[tuple[0]] = tuple[1];
    }
    return map;
  }

  // private convertMapToList(map: object): [string, string][] {
  //   const list: [string, string][] = [];
  //   for(let key in map) {
  //     if ()
  //   }
  //   return list;
  // }

  public populateValue(personalInfo: PersonalInfoData): void {
    // console.log(this.form);
    const formValue = {
      user: {
        surname: personalInfo.user.surname,
        name: personalInfo.user.name
      },
      phoneNumber: personalInfo.phoneNumber,
      dateOfBirth: formatDate(personalInfo.dateOfBirth, 'dd.MM.yyyy', 'en-US'),
      city: personalInfo.city,
      education: personalInfo.education,
      faculty: personalInfo.faculty,
      educationYears: [personalInfo.firstEducationYear, personalInfo.lastEducationYear],
      skills: [],
      languages: []
    };
    // console.log(formValue);
    const skills: FormArray = (this.form.get('skills') as FormArray);
    const languages: FormArray = (this.form.get('languages') as FormArray);
    skills.controls.splice(0);
    languages.controls.splice(0);
    this.form.setValue(formValue);
    for (let skill of Object.getOwnPropertyNames(personalInfo.skills)) {
      skills.push(new FormArray([new FormControl(skill), new FormControl(personalInfo.skills[skill])]));
    }
    for (let language of Object.getOwnPropertyNames(personalInfo.languages)) {
      languages.push(new FormArray([new FormControl(language), new FormControl(personalInfo.languages[language])]));
    }
  }
}
