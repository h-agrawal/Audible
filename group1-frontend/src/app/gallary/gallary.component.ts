import { Component, OnInit } from '@angular/core';
import { LoginService } from '../loginpage/login.service';
import { ServiceService } from '../landing-page/service.service';
import { Audiobook } from '../model/Audiobook';
import { Customer } from '../model/Customer';

@Component({
  selector: 'app-gallary',
  templateUrl: './gallary.component.html',
  styleUrls: ['./gallary.component.css']
})
export class GallaryComponent implements OnInit{

  audiobook!:Audiobook[]
  customer!:Customer
  criteria='title'

  constructor(private loginService:LoginService, private service:ServiceService){}

  ngOnInit(): void {
    this.loginService.customer.subscribe(responce=>this.customer=responce)
    this.service.audiobook().subscribe(response=>this.audiobook=response)
    
  }

}
