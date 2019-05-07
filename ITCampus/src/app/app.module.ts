import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { PersonalInfoComponent } from './personal-info/personal-info.component';
// import { IntervalPipe} from './personal-info/yearInterval';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PersonalInfoComponent,
    // IntervalPipe
  ],
  imports: [
    BrowserModule,
    // IntervalPipe
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
