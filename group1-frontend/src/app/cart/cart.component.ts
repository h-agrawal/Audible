import { Component, OnInit } from '@angular/core';
import { CartserviceService } from '../cartservice.service';
import { LoginService } from '../loginpage/login.service';
import { Customer } from '../model/Customer';
import { Cart } from '../model/Cart';
import { ServiceService } from '../landing-page/service.service';
import { Audiobook } from '../model/Audiobook';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{



dcart=new Cart()
deleteSuccess!:string
errorMessage!:string
customer!:Customer
cart!:Cart[]
total!:number
audiobook!:Audiobook[]


constructor(private cartService:CartserviceService, private loginService:LoginService,
  private service:ServiceService,private router:Router)
  {}

  ngOnInit(): void 
  {
    this.loginService.customer.subscribe(response=>
      {
        this.customer=response
        this.cartService.getItem(this.customer.id).subscribe(response=>
          {
            this.cart=response
            this.service.getAudiobookById(this.cart.map(m=>m.audiobookId)).subscribe(rep=>
              {
                this.audiobook=rep
                this.total=rep.reduce((a,i)=>a+i.price,0)
              })
          },
          error=>{
          this.errorMessage=error.error.errorMessage
          this.audiobook=[]})
      })
    
  }



  payment() {
    this.router.navigate(['/payment', this.total])
    }



    gallary() {
      this.router.navigate(['/gallary'])
    }


  delete(arg0: number) {
    this.dcart.audiobookId=arg0
    this.dcart.customerId=this.customer.id
  this.cartService.deleteItem(this.dcart).subscribe(response=>this.deleteSuccess=response.message)
  this.ngOnInit()
  }

}
