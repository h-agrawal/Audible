import { Component } from '@angular/core';
import { LoginService } from '../loginpage/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {
isLoggedIn = false;
constructor(private loginService:LoginService){
  loginService.customer.subscribe(respose=>this.isLoggedIn=respose.username!=null)
}

}
