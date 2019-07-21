import { Component, OnInit, Input } from '@angular/core';
import { UserData } from '../user.data';
import { PersonalInfoData } from './personal-info.data';
import { PersonalInfoService } from './personal-info.service';
import { PageDataService } from '../page-web.service';
import { UserService } from '../user.service';


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
  savedPersonalInfo: PersonalInfoData;
  savedUser: UserData;
  isLoaded: boolean = false;
  editMode: boolean = false;
  editSaveName: string = 'Edit';

  constructor(private personalInfoService: PersonalInfoService,
      private userService: UserService) {
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
    this.personalInfoService.getPersonalInfoObservable().subscribe(personalInfo => {
      this.savedPersonalInfo = personalInfo;
      this.personalInfo = JSON.parse(JSON.stringify(this.savedPersonalInfo));
    });
    this.userService.getUserByLogin().subscribe(user => {
      this.savedUser = user;
      this.user = JSON.parse(JSON.stringify(this.savedUser));
      this.isLoaded = true;
    })
  }

  onEditSaveClick() {
    this.editMode = !this.editMode;
    if (this.editMode === false) {
      this.editSaveName = 'Edit';
      this.userService.updateUser(this.user).subscribe(succeed => {
        if (succeed) {
          this.savedUser = this.user;
        }
        this.user = JSON.parse(JSON.stringify(this.savedUser));
      });
      this.personalInfoService.updatePersonalInfo(this.personalInfo, this.savedUser.id)
        .subscribe(succeed => {
          console.log("Update personal info : succeed?" + succeed);
        if (succeed) {
          this.savedPersonalInfo = this.personalInfo;
        }
        this.personalInfo = JSON.parse(JSON.stringify(this.savedPersonalInfo));
      });
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
