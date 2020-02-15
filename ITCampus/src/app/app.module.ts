import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthService } from './auth.service';
import { DataEntryComponent } from './data-entry/data-entry.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { AuthInterceptor } from './interceptors/auth-interceptor';
import { CorsInterceptor } from './interceptors/cors-interceptor';
import { LoginComponent } from './login/login.component';
import { LogoComponent } from './logo/logo.component';
import { MapEntryComponent } from './map-entry/map-entry.component';
import { PersonalInfoComponent } from './personal-info/personal-info.component';
import { ProfileComponent } from './profile/profile.component';
import { ProjectsComponent } from './projects/projects.component';
import { FilterPipe } from './subjects/filter.pipe';
import { PagePipe } from './subjects/page.pipe';
import { SubjectsComponent } from './subjects/subjects.component';
import { TuplePipe } from './tuple.pipe';




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
    ProfileComponent,
    LoginComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CorsInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
