import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Audiobook } from '../model/Audiobook';
import { Observable, catchError, throwError } from 'rxjs';
import { Customer } from '../model/Customer';
import { Message } from '../model/Message';
import { Password } from '../model/Password';

@Injectable({
    providedIn: 'root'
})
export class HomeService {

    constructor(private http: HttpClient) {

    }
    changePassword(id:number,password:Password){
        const url='http://localhost:2000/customer/updatepassword/'
        return this.http.post<Message>(url+id,password).pipe(catchError(this.handleError));
    }

    fetchAudiobook(audiobookIds: number[]): Observable<Audiobook[]> {
        const url = 'http://localhost:9500/audiobook/library';
        console.log(audiobookIds)
        return this.http.put<Audiobook[]>(url, audiobookIds).pipe(catchError(this.handleError));

    }

    fetchProfile(id:number): Observable<Customer>{
        return this.http.get<Customer>("http://localhost:9400/customer/profile/"+id)
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
