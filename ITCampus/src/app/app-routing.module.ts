import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './auth-guard.service';

const appRoutes: Routes = [
  { path: 'tordek', component: HomeComponent/*, canActivate:[AuthGuardService] */},
    { path: 'login', component: LoginComponent },
    { path: '**', redirectTo: 'tordek' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
