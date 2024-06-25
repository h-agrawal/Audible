import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../model/Customer';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) {

  }

  registerCustomer(customer: Customer): Observable<Customer> {
      const url ='http://localhost:9400/customer/register';
      return this.http.post<Customer>(url, customer).pipe(catchError(this.handleError));

  }
  private handleError(err: HttpErrorResponse) {
      console.log(err)
      let errMsg: string = '';
      if (err.error instanceof Error) {
          console.log("1" + err.error.message)
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

              errMsg = err.error.message;

          }
      }
      return throwError(errMsg);
  }
}
