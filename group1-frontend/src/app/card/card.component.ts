import { Component, Input, OnInit } from '@angular/core';
import { Audiobook } from '../model/Audiobook';
import { LoginService } from '../loginpage/login.service';
import { Router } from '@angular/router';
import { Customer } from '../model/Customer';
import { Cart } from '../model/Cart';
import { CartserviceService } from '../cartservice.service';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  successMessage!: string
  customer!: Customer
  cart = new Cart()

  constructor(private loginService: LoginService, private router: Router,
    private cartService: CartserviceService) { }

  ngOnInit(): void {

  }


  addToCart(id: number) {
    if (this.loginService.canLogin()) 
    {
      this.loginService.customer.subscribe(response => 
        {
          this.customer = response
          this.cart.customerId = response.id
          this.cart.audiobookId = id
          this.cartService.addItem(this.cart).subscribe(response => 
            {
              this.successMessage = response.message
              
              
        })
      })
    }
    else {
      this.router.navigate(['/login'])
    }

  }

  @Input() audioBook!: Audiobook;

}
