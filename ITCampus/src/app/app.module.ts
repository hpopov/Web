import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { PersonalInfoComponent } from './personal-info/personal-info.component';
import { SubjectsComponent } from './subjects/subjects.component';
import { ProjectsComponent } from './projects/projects.component';
import { LogoComponent } from './logo/logo.component';
import { FooterComponent } from './footer/footer.component';
import { TuplePipe } from './tuple.pipe';
import { PagePipe } from './subjects/page.pipe';
import { FilterPipe } from './subjects/filter.pipe';
import { DataEntryComponent } from './data-entry/data-entry.component';
import { MapEntryComponent } from './map-entry/map-entry.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PersonalInfoComponent,
    SubjectsComponent,
    ProjectsComponent,
    LogoComponent,
    FooterComponent,
    TuplePipe,
    PagePipe,
    FilterPipe,
    DataEntryComponent,
    MapEntryComponent,
  ],
  imports: [
    BrowserModule, FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
