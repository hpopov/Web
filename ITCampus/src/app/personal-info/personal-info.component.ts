import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Education } from './education';
import { YearInterval, IntervalPipe} from './yearInterval';


@Component({
  selector: 'personal-info-component',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.scss']
})
export class PersonalInfoComponent implements OnInit {

  constructor() { }
  user: User = {
    id: 0,
    name: 'Hryhorii',
    surname: 'Popov'
  };

  phoneNumber: string = '(+38) 093-854-55-64';
  dateOfBirth: Date = new Date('25.03.1999');
  city:string = 'Kyiv';
  education: Education = new Education('NTUU', '"Igor Sikorsky Kyiv Polytechnic Institute"');
  faculty: string = 'Institute of Applied System Analysis';
  educationYear: YearInterval = new YearInterval(2016, 2020);
  skills: Object = {
  	'Java': 'Intermediate',
  	'Java Script': 'Background',
  	'Paint': 'Confident'
  };
  languages: Object = {
  	'English': 'Upper-Intermediate',
  	'Ukrainian': 'Native',
  	'Russian': 'Native'
  };
  // img: ImageBitmapSource = new ImageBitmap();

  ngOnInit() {
  }

}
