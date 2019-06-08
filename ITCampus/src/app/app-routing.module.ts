import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './auth-guard.service';

const appRoutes: Routes = [
  { path: '', component: HomeComponent/*, canActivate:[AuthGuardService] */},
   //{ pathMatch: 'full', path: '', redirectTo: '/home', canDeactivate:[AuthGuardService] },
    { path: 'login', component: LoginComponent },
    { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
