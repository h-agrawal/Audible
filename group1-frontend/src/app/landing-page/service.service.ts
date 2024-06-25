import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Audiobook } from '../model/Audiobook';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  dummyAudiobook = [new Audiobook()]
    audiobooks: BehaviorSubject<Audiobook[]> = new BehaviorSubject<Audiobook[]>(this.dummyAudiobook);


    setData(newAudiobook:any){
        this.audiobooks.next(newAudiobook);
    }

  constructor(private http:HttpClient) { }

  audiobook():Observable<Audiobook[]>{
    return this.http.get<Audiobook[]>("http://localhost:2000/audiobook/sampleaudio").
    pipe(catchError(this.handleError))
  }


  getAudiobookById(id:number[]):Observable<Audiobook[]>{
    return this.http.put<Audiobook[]>("http://localhost:2000/audiobook/library",id).
    pipe(catchError(this.handleError))
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

