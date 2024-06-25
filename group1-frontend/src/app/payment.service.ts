import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cart } from './model/Cart';
import { Observable, catchError, throwError } from 'rxjs';
import { Message } from './model/Message';
import { Payment } from './model/Payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http:HttpClient) { }

  addAudiobookInAccount(carts:Cart[]):Observable<Message>{
    return this.http.post<Message>("http://localhost:9400/customer/addPurchase",carts).pipe(catchError(this.handleError));
  }
  addCard(card:Payment):Observable<Message>{
    return this.http.post<Message>("http://localhost:9700/payment/saveCard",card).pipe(catchError(this.handleError))
  }

  deleteCart(id:number):Observable<Message>{
    return this.http.delete<Message>("http://localhost:9600/cart/Items/"+id).pipe(catchError(this.handleError))
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
