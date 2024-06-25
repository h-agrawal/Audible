import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { AccessGuardService } from './auth.guard';
import { GallaryComponent } from './gallary/gallary.component';
import { CartComponent } from './cart/cart.component';
import { PaymentComponent } from './payment/payment.component';
import { ProfileComponent } from './profile/profile.component';
import { PasswordmanagerComponent } from './passwordmanager/passwordmanager.component';

const routes: Routes = [
  { path: '', redirectTo: 'landingpage', pathMatch: 'full' },
  { path: 'landingpage', component:LandingPageComponent },
  { path: 'login', component:LoginpageComponent },
  { path: 'home', component:HomeComponent , canActivate:[AccessGuardService]},
  { path: 'register', component:RegisterComponent },
  { path: 'cart', component:CartComponent , canActivate:[AccessGuardService] },
  { path: 'gallary', component:GallaryComponent , canActivate:[AccessGuardService]},
  { path: 'payment/:item', component:PaymentComponent , canActivate:[AccessGuardService]},
  { path: 'profile', component:ProfileComponent , canActivate:[AccessGuardService]},
  { path: 'password', component:PasswordmanagerComponent , canActivate:[AccessGuardService]},
  { path: '**',redirectTo: 'landingpage', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
