import { Component, OnInit } from '@angular/core';
import { ServiceService } from './service.service';
import { Audiobook } from '../model/Audiobook';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {



  audiobook: Audiobook[] = []
  errorMessage = ''
  criteria='title'

  constructor(private service: ServiceService) { }

  ngOnInit(): void {
    this.callService()
    console.log(this.criteria)
  }
  
  callService() {
    this.service.audiobook().subscribe(response => {
      this.audiobook = response;
      console.log(response);
      this.service.setData(response)
    },
      (error) => {
        console.log("hi" + error)
        this.errorMessage = <any>error;
      }
    )
  }



}
