import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { Customer } from '../model/Customer';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {
  customer!:Customer
    loginForm!: FormGroup;
    errorMessage!: string;
    successMessage!: string;
    // tryToLogin: boolean = false;
    constructor(private fb: FormBuilder, private loginService: LoginService,
        private router: Router) {

    }

    ngOnInit() {
        if(this.loginService.canLogin()){
            this.router.navigate(['/home']);
        }
        this.createForm();

    }
    createForm() {

        this.loginForm = this.fb.group({
            username: ['', [Validators.required]],
            password: ['', [Validators.required]]
        });
    }



    login() {
        // this.tryToLogin = true;
        this.errorMessage = "";
        this.successMessage = "";
        this.loginService.login(this.loginForm.value).subscribe(

            (response) => {

                this.customer = response
                // sessionStorage.setItem("customer", JSON.stringify(this.customer));
                this.successMessage="Login Success"
                console.log(response)
                this.loginService.setData(this.customer)
                localStorage.setItem('userName',this.customer.username);
                // this.tryToLogin = false;
                setTimeout(()=>this.router.navigate(['home']),2000)
                
            },
            (error) => {
                console.log("hi" + error)
                // this.tryToLogin = false;
                // this.errorMessage = <any>error;
                this.errorMessage="Username/Password is invalid"
            }
        )
    }


}
