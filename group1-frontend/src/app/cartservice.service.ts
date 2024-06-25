import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cart } from './model/Cart';
import { Message } from './model/Message';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartserviceService {

  constructor(private http:HttpClient) { }

  addItem(cart:Cart):Observable<Message>{
    return this.http.post<Message>("http://localhost:9600/cart/add", cart).pipe(catchError(this.handleError));
  }

  deleteItem(cart:Cart):Observable<Message>{
    return this.http.post<Message>("http://localhost:9600/cart/delete", cart).pipe(catchError(this.handleError));
  }

  getItem(id:number):Observable<Cart[]>{
    return this.http.get<Cart[]>("http://localhost:9600/cart/Items/"+id)
  }

  emptyCart(id:number):Observable<Message>{
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
