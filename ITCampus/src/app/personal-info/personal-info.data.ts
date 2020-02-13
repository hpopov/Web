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
  skills: [string, string][];
  languages: [string, string][];
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

  constructor(personalInfo: PersonalInfoData) {
    this.populateValue(personalInfo);
  }

  public asData(username: string): PersonalInfoData {
    return {
      city: this.form.get('city').value,
      dateOfBirth: this.form.get('dateOfBirth').value,
      education: this.form.get('education').value,
      faculty: this.form.get('faculty').value,
      firstEducationYear: (this.form.get('educationYears') as FormArray).at(0).value,
      lastEducationYear: (this.form.get('educationYears') as FormArray).at(1).value,
      phoneNumber: this.form.get('phoneNumber').value,
      languages: (this.form.get('languages') as FormArray).getRawValue(),
      skills: (this.form.get('skills') as FormArray).getRawValue(),
      user: {
        login: username,
        name: this.form.get(['user', 'name']).value,
        surname: this.form.get(['user', 'surname']).value
      }
    }
  }

  public populateValue(personalInfo: PersonalInfoData): void {
    this.form.setValue({
      user: {
        surname: personalInfo.user.surname,
        name: personalInfo.user.name
      },
      phoneNumber: personalInfo.phoneNumber,
      dateOfBirth: personalInfo.dateOfBirth,
      city: personalInfo.city,
      education: personalInfo.education,
      faculty: personalInfo.faculty,
      educationYears: [personalInfo.firstEducationYear, personalInfo.lastEducationYear]//? 
    });
    const skills: FormArray = (this.form.get('skills') as FormArray);
    skills.reset([]);
    personalInfo.skills.forEach(value => {
      skills.push(new FormControl(value));
    });
    const languages: FormArray = (this.form.get('languages') as FormArray);
    languages.reset([]);
    personalInfo.languages.forEach(value => {
      languages.push(new FormControl(value));
    });
  }
}
