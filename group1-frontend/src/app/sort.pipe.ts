import { Pipe, PipeTransform } from '@angular/core';
import { Audiobook } from './model/Audiobook';

@Pipe({
  name: 'sort'
})
export class SortPipe implements PipeTransform {

  transform(value: Audiobook[], arg:string): Audiobook[] {

    if(arg=='title'){
      return value.sort((a: any, b: any) => {
        if (a.title < b.title) {
            return -1;
        } else if (a.title > b.title) {
            return 1;
        } else {
            return 0;
        }
    });
    }
    else if(arg=='author'){
      return value.sort((a: any, b: any) => {
        if (a.author < b.author) {
            return -1;
        } else if (a.author > b.author) {
            return 1;
        } else {
            return 0;
        }
    });
    }
    else if(arg=='pricehl'){
      return value.sort((a: any, b: any) => {
        if (a.price < b.price) {
            return 1;
        } else if (a.price > b.price) {
            return -1;
        } else {
            return 0;
        }
    });
    }
    else if(arg=='pricelh'){
      return value.sort((a: any, b: any) => {
        if (a.price < b.price) {
            return -1;
        } else if (a.price > b.price) {
            return 1;
        } else {
            return 0;
        }
    });
    }
    return value;
  }

}
