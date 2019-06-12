import { Component, OnInit, Input } from '@angular/core';
import { UserData } from '../user.data';
import { PersonalInfoData } from './personal-info.data';
import { PersonalInfoService } from './personal-info.service';
import { PageDataService } from '../page-data.service';


@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.scss'],
  providers: [PersonalInfoService]
})
export class PersonalInfoComponent implements OnInit {
  user: UserData;
  @Input() editable: boolean;
  personalInfo: PersonalInfoData;
  tempPersonalInfo: PersonalInfoData;
  isLoaded: boolean = false;
  editMode: boolean = false;
  editSaveName: string = 'Edit';

  constructor(private pageDataService: PageDataService) {
    this.user = {
      id: -1,
      login: '',
      name: '',
      surname: '',
      authorities: []
    };
    this.personalInfo = {
      city: null,
      dateOfBirth: null,
      education: null,
      educationYear: null,
      faculty: null,
      languages: null,
      phoneNumber: null,
      skills: null
    }
   }

  ngOnInit() {
    this.pageDataService.getObservablePageData().subscribe(pageData => {
      this.user = pageData.user;
      this.personalInfo = pageData.personalInfo;
      this.tempPersonalInfo = JSON.parse(JSON.stringify(this.personalInfo));
      this.isLoaded = true;
    });
  }

  onEditSaveClick() {
    this.editMode = !this.editMode;
    if (this.editMode === false) {
      this.editSaveName = 'Edit';
      // this.personalInfoService.onUpdateEmitter.emit();
    } else {
      this.editSaveName = 'Save';
    }
  }

  convertDate(date: string) : Date{
    return new Date(Date.UTC(Number.parseFloat(date.slice(6,10)), Number.parseFloat(date.slice(3,5)),
        Number.parseFloat(date.slice(0,2))));
  }

  updateUserProperty(event) {
    this.user[event.key] = event.value;
    console.log('User');
    console.log(this.user);
  }

  updatePersonalProperty(event) {
    this.personalInfo[event.key] = event.value;
    console.log('Personal');
    console.log(this.personalInfo);
  }
}
