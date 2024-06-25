import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { ServiceService } from './landing-page/service.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule }     from '@angular/common/http';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { LoginService } from './loginpage/login.service';
import { HomeService } from './home/home.service';
import { RegisterService } from './register/register.service';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { CardComponent } from './card/card.component';
import { CartComponent } from './cart/cart.component';
import { AccessGuardService } from './auth.guard';
import { GallaryComponent } from './gallary/gallary.component';
import { FilteraudiobookPipe } from './filteraudiobook.pipe';
import { FilterPipe } from './filter.pipe';
import { PaymentComponent } from './payment/payment.component';
import { PaymentService } from './payment.service';
import { CartserviceService } from './cartservice.service';
import { ProfileComponent } from './profile/profile.component';
import { PasswordmanagerComponent } from './passwordmanager/passwordmanager.component';
import { SortComponent } from './sort/sort.component';
import { SortPipe } from './sort.pipe';
import { CompComponent } from './comp/comp.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    NavBarComponent,
    LoginpageComponent,
    RegisterComponent,
    CardComponent,
    HomeComponent,
    CartComponent,
    GallaryComponent,
    FilteraudiobookPipe,
    FilterPipe,
    PaymentComponent,
    ProfileComponent,
    PasswordmanagerComponent,
    SortComponent,
    SortPipe,
    CompComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [ServiceService,LoginService,HomeService,RegisterService,
    AccessGuardService,PaymentService,CartserviceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
