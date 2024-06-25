import { Pipe, PipeTransform } from '@angular/core';
import { Audiobook } from './model/Audiobook';
import { Customer } from './model/Customer';

@Pipe({
  name: 'filteraudiobook'
})
export class FilteraudiobookPipe implements PipeTransform {

  transform(value: Audiobook[], customer:Customer): Audiobook[] {
    if(customer && customer.audiobookId){
    return value.filter(v=>{ 
      for (let i of customer?.audiobookId)
      { console.log(i,v)
        if (i==v.id) 
        {
          return false
        }}
      return true})

  }else{
    return value
  }

}}
