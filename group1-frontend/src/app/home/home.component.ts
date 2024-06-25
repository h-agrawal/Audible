import { Component, OnInit } from '@angular/core';
import { Customer } from '../model/Customer';
import { LoginService } from '../loginpage/login.service';
import { HomeService } from './home.service';
import { Audiobook } from '../model/Audiobook';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  emptyHome!: string

  constructor(private loginService: LoginService, private homeService: HomeService) {

  }
  customer!: Customer
  audiobooks!: Audiobook[]
  ngOnInit(): void {
    this.loginService.customer.subscribe
      (
        response => {
          this.customer = response;
          if (this.customer.audiobookId == null) {
            this.emptyHome = "No book present in your account"
          }
          else {
            this.homeService.fetchAudiobook(this.customer.audiobookId).subscribe(response => {
              this.audiobooks = response; console.log(response)
            }
            )
          }
        }
      )
  }





}
