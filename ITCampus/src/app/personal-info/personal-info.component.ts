import { Component, OnInit, Input } from '@angular/core';
import { UserData } from '../user.data';
import { PersonalInfoData } from './personal-info.data';
import { PersonalInfoService } from './personal-info.service';
import { formatDate } from '@angular/common';


@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.scss'],
  providers: [PersonalInfoService]
})
export class PersonalInfoComponent implements OnInit {
  @Input() user: UserData;
  personalInfo: PersonalInfoData;
  tempPersonalInfo: PersonalInfoData;
  editMode: boolean = false;
  editSaveName: string = 'Edit';

  constructor(private personalInfoService: PersonalInfoService) { }

  ngOnInit() {
    this.personalInfo = this.personalInfoService.getPersonalInfoByUserId(this.user.id);
    this.tempPersonalInfo = JSON.parse(JSON.stringify(this.personalInfo));
  }

  onEditSaveClick() {
    // if (this.editMode) {
    //   console.log(this.user);
    //   console.log(this.personalInfo);
    // }
    this.editMode = !this.editMode;
    if (this.editMode === false) {
      this.editSaveName = 'Edit';
      this.personalInfoService.onUpdateEmitter.emit();
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
