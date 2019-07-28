import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { ProfileResolver } from './profile/profile-resolver.service';


const appRoutes: Routes = [
  { path: 'profile/:login', component: ProfileComponent,
      resolve: {profile: ProfileResolver}/*, canActivate:[AuthGuardService] */},
    { path: 'login', component: LoginComponent },
    { path: '', pathMatch: 'full', redirectTo: '/profile/tordek'},
    { path: '**', redirectTo: '/profile/tordek' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
