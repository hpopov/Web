import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthInterceptor } from './auth-interceptor';

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
    HomeComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
}
],
  bootstrap: [AppComponent]
})
export class AppModule { }
