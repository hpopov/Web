import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { UserData } from '../shared/user/user.data';
import { PersonalInfoData } from './personal-info.data';
import { PersonalInfoService } from './personal-info.service';
import { ProfileService } from '../profile/profile.service';
import { UserService } from '../shared/user/user.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.scss'],
  providers: [PersonalInfoService]
})
export class PersonalInfoComponent implements OnInit, OnDestroy {

  user: UserData;
  @Input() editable: boolean;
  personalInfo: PersonalInfoData;
  personalInfoSubscription: Subscription;
  @Input("personalInfo")savedPersonalInfo: PersonalInfoData;
  userSubscription: Subscription;
  @Input("user")savedUser: UserData;
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
    this.user = JSON.parse(JSON.stringify(this.savedUser));
    this.personalInfo = JSON.parse(JSON.stringify(this.savedPersonalInfo));
    this.userSubscription = this.userService.getUserAsObservable().subscribe(user => {
      this.savedUser = user;
      this.user = JSON.parse(JSON.stringify(this.savedUser));      
    });
    this.personalInfoSubscription = 
      this.personalInfoService.getPersonalInfoAsObservable().subscribe(personalInfo => {
        this.savedPersonalInfo = personalInfo;
        this.personalInfo = JSON.parse(JSON.stringify(this.savedPersonalInfo));
      });
    
  }

  ngOnDestroy(): void {
    this.personalInfoSubscription.unsubscribe();
    this.userSubscription.unsubscribe();
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
