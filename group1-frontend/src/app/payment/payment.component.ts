import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CartserviceService } from '../cartservice.service';
import { LoginService } from '../loginpage/login.service';
import { Customer } from '../model/Customer';
import { Cart } from '../model/Cart';
import { PaymentService } from '../payment.service';
import { Message } from '../model/Message';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {

  successMessage!: string
  customer!: Customer
  carts!: Cart[]
  message!: Message
  successMessageCard!: string;
  listAudiobook:number[]=[]


  payment() {

    this.loginService.customer.subscribe(reponse1 => 
      
        this.customer = reponse1)
        this.paymentService.addCard(this.cardForm.value).subscribe(
        responce2 => this.successMessageCard = responce2.message)

        this.cartService.getItem(this.customer.id).subscribe(reponse3 => 
          {
            this.carts = reponse3
            console.log(this.carts)
            this.paymentService.addAudiobookInAccount(this.carts).subscribe(
            response4 => 
            {
              this.successMessage = response4.message

              this.carts.forEach(element => this.listAudiobook.push(element.audiobookId));
              if(this.customer.audiobookId)
              { this.customer.audiobookId.forEach(element => this.listAudiobook.push(element))}
              this.customer.audiobookId=this.listAudiobook
              this.loginService.setData(this.customer)
              this.paymentService.deleteCart(this.customer.id).subscribe(
              reponse5 => 
              {
                this.message = reponse5
                setTimeout(() => this.route.navigate(['/home']), 2000)
              }
            )
          }
        )
      
    })
    
  }
  cardForm!: FormGroup;
  constructor(private fb: FormBuilder, private router: ActivatedRoute, private paymentService: PaymentService, private cartService: CartserviceService
    , private loginService: LoginService, private route: Router) {

  }
  price!: number
  ngOnInit() {
    this.loginService.customer.subscribe(reponse => {
      this.customer = reponse
      this.createForm(this.customer);
    })
    this.router.params.subscribe(response => this.price = response['item'])

  }

  createForm(customer: Customer) {

    this.cardForm = this.fb.group({
      customerId: [customer.id],
      cardNumber: ['', [Validators.required, Validators.pattern('[0-9]{16}')]],
      name: ['', [Validators.required]],
      month: ['', [Validators.required, Validators.min(1), Validators.max(12)]],
      year: ['', [Validators.required]],
      cvv: ['', [Validators.required, Validators.pattern('[0-9]{3}')]]
    });
  }

}
