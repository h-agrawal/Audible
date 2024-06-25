import { Component, OnInit } from '@angular/core';
import { HomeService } from '../home/home.service';
import { Customer } from '../model/Customer';
import { LoginService } from '../loginpage/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  customer!:Customer

  constructor(private homeService:HomeService,private loginService:LoginService){}

  ngOnInit(): void {
    this.loginService.customer.subscribe(response=>this.customer=response)
    this.homeService.fetchProfile(this.customer.id).subscribe(resonse=>this.customer=resonse)
  }

  

}
