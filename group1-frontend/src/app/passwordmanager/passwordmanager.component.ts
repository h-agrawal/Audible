import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from '../model/Customer';
import { LoginService } from '../loginpage/login.service';
import { Router } from '@angular/router';
import { PasswordValidate } from '../model/PasswordValidate';
import { HomeService } from '../home/home.service';

@Component({
  selector: 'app-passwordmanager',
  templateUrl: './passwordmanager.component.html',
  styleUrls: ['./passwordmanager.component.css']
})
export class PasswordmanagerComponent {

    customer!:Customer
    passwordForm!: FormGroup;
    errorMessage!: string;
    successMessage!: string;
    // tryToLogin: boolean = false;
    constructor(private fb: FormBuilder, private loginService: LoginService,
        private router: Router, private homeService:HomeService) {

    }

    ngOnInit() {
      this.loginService.customer.subscribe(

        (response) => {

            this.customer = response})

        this.createForm();

    }
    createForm() {

        this.passwordForm = this.fb.group({
          previousPassword: ['', [Validators.required]],
          currentPassword: ['', [Validators.required, PasswordValidate.validatePassword]]
        });
    }



    change() {
        this.errorMessage = "";
        this.successMessage = "";
        this.homeService.changePassword(this.customer.id,this.passwordForm.value).subscribe(

            (response) => {

              this.successMessage = response.message
                console.log(response)
                setTimeout(()=>this.router.navigate(['home']),2000)
                
            },
            (error) => {
                console.log("hi" + error)
                this.errorMessage =<any> error;
            }
        )
    }



}
