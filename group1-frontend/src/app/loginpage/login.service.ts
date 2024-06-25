import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../model/Login';
import { Customer } from '../model/Customer';
import { BehaviorSubject, Observable, catchError, throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
    dummyCustomer = new Customer()
    logged=false
    customer: BehaviorSubject<Customer> = new BehaviorSubject<Customer>(this.dummyCustomer);


    setData(newCustomer:Customer){
        this.customer.next(newCustomer);
    }

    canLogin(){
        this.logged=false
        this.customer.subscribe(response=>this.logged=response.username!=null)
        return this.logged
    }


  constructor(private http: HttpClient) {

  }
  login(login:Login): Observable<Customer> {
      const url = 'http://localhost:9400/customer/login';

      return this.http.post<Customer>(url, login)
          .pipe(catchError(this.handleError));

  }
  private handleError(err: HttpErrorResponse) {
      console.log(err)
      let errMsg: string = '';
      if (err.error instanceof Error) {
          errMsg = err.error.message;
          console.log(errMsg)
      }
      else if (typeof err.error === 'string') {
          errMsg = JSON.parse(err.error).errorMessage
      }
      else {
          if (err.status == 0) {
              errMsg = "A connection to back end can not be established.";
          } else {
              errMsg = err.error.errorMessage;
          }
      }
      return throwError(errMsg);
  }
}
