import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from '../shared/user/user.service';
import { PersonalInfoData, PersonalInfoFormGroup } from './personal-info.data';
import { PersonalInfoService } from './personal-info.service';


@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.scss']
})
export class PersonalInfoComponent implements OnInit, OnDestroy {

  @Input() editable: boolean;
  personalInfoFormGroup: PersonalInfoFormGroup;
  personalInfoSubscription: Subscription;
  savedPersonalInfo: PersonalInfoData;
  userAvatarUrl: string = '';
  isLoaded: boolean = true;
  editMode: boolean = false;
  editSaveName: string = 'Edit';

  constructor(private personalInfoService: PersonalInfoService,
      private userService: UserService) {
    // this.personalInfo = {s
    //   city: null,
    //   dateOfBirth: null,
    //   education: null,
    //   educationYear: null,
    //   faculty: null,
    //   languages: null,
    //   phoneNumber: null,
    //   skills: null
    // }
   }

  ngOnInit() {
    this.personalInfoFormGroup = new PersonalInfoFormGroup();
    // this.userSubscription = this.userService.getUserAsObservable().subscribe(user => {
    //   this.savedUser = user;
    //   this.user = JSON.parse(JSON.stringify(this.savedUser));      
    // });
    this.personalInfoSubscription = 
      this.personalInfoService.personalInfo.asObservable().subscribe(personalInfo => {
        console.log(personalInfo);
        this.savedPersonalInfo = personalInfo;
        // this.personalInfo = JSON.parse(JSON.stringify(this.savedPersonalInfo));
        this.personalInfoFormGroup.populateValue(personalInfo);
        this.userAvatarUrl = this.userService.getUserAvatarUrl(personalInfo.user.login);
      });
    
  }

  ngOnDestroy(): void {
    this.personalInfoSubscription.unsubscribe();
  }

  onEditSaveClick() {
    if (this.editMode === true) {
      this.editSaveName = 'Edit';
      const username = this.savedPersonalInfo.user.login;
      this.personalInfoService.updatePersonalInfo(this.personalInfoFormGroup.asData(username));
    } else {
      this.editSaveName = 'Save';
    }
    this.editMode = !this.editMode;
  }

  getUserAvatarUrl(username: string): string {
    return this.userService.getUserAvatarUrl(username);
  }

  // updateUserProperty(event) {
  //   this.user[event.key] = event.value;
  //   console.log('User');
  //   console.log(this.user);
  // }

  // updatePersonalProperty(event) {
  //   this.personalInfo[event.key] = event.value;
  //   console.log('Personal');
  //   console.log(this.personalInfo);
  // }
}
