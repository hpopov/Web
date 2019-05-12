import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { PersonalInfoComponent } from './personal-info/personal-info.component';
import { SubjectsComponent } from './subjects/subjects.component';
import { ProjectsComponent } from './projects/projects.component';
import { LogoComponent } from './logo/logo.component';
import { FooterComponent } from './footer/footer.component';
// import { IntervalPipe} from './personal-info/yearInterval';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PersonalInfoComponent,
    SubjectsComponent,
    ProjectsComponent,
    LogoComponent,
    FooterComponent,
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
