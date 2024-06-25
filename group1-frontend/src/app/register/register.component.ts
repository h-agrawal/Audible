import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from './register.service';
import { PasswordValidate } from '../model/PasswordValidate';
import { Router } from '@angular/router';
import { Customer } from '../model/Customer';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent {
    customer!: Customer
    registerForm!: FormGroup;
    cpassword!: string
    errorMessage!: string;
    successMessage!: string;
    constructor(private fb: FormBuilder, private registerService: RegisterService, private router: Router) {

    }

    ngOnInit() {
        this.createForm();

    }
    createForm() {

        this.registerForm = this.fb.group({
            email: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.(com|in|org)')]],
            name: ['', [Validators.required, Validators.pattern('[A-Z][a-z]*( [A-Z][a-z]*)*')]],
            password: this.fb.group({
                currentPassword: ['', [Validators.required, PasswordValidate.validatePassword]]
            }),
            username: ['', [Validators.required, Validators.pattern('[a-z0-9@#$&_]+')]]
        });
    }

    register() {
        this.errorMessage = "";
        this.successMessage = "";

        this.registerService.registerCustomer(this.registerForm.value)
            .subscribe(
                message => {
                    this.customer = message;
                    this.successMessage = "Registration Successfull with username : " + this.customer.username;
                    this.registerForm.reset();

                    this.router.navigate(['/login']);
                }
                , error => {
                    this.errorMessage = error;
                    console.log(error);
                }
            )

    }

}
