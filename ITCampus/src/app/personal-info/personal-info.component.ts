import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { WebRequestService } from '../web-request.service';
import { PersonalInfoData, PersonalInfoFormGroup } from './personal-info.data';
import { PersonalInfoService } from './personal-info.service';
import { UserService } from '../shared/user/user.service';


@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.scss'],
  providers: [PersonalInfoService]
})
export class PersonalInfoComponent implements OnInit, OnDestroy {

  @Input() editable: boolean;
  personalInfoFormGroup: PersonalInfoFormGroup;
  personalInfoSubscription: Subscription;
  @Input("personalInfo")savedPersonalInfo: PersonalInfoData;
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
    this.personalInfoFormGroup = new PersonalInfoFormGroup(this.savedPersonalInfo);
    // this.userSubscription = this.userService.getUserAsObservable().subscribe(user => {
    //   this.savedUser = user;
    //   this.user = JSON.parse(JSON.stringify(this.savedUser));      
    // });
    this.personalInfoSubscription = 
      this.personalInfoService.personalInfo.asObservable().subscribe(personalInfo => {
        this.savedPersonalInfo = personalInfo;
        // this.personalInfo = JSON.parse(JSON.stringify(this.savedPersonalInfo));
        this.personalInfoFormGroup.populateValue(personalInfo);
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

  convertDate(date: string) : Date{
    return new Date(Date.UTC(Number.parseFloat(date.slice(6,10)), Number.parseFloat(date.slice(3,5)),
        Number.parseFloat(date.slice(0,2))));
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
