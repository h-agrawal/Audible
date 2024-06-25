import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-sort',
  templateUrl: './sort.component.html',
  styleUrls: ['./sort.component.css']
})
export class SortComponent {

  @Output() sortBy = new EventEmitter<string>()

  value="title"

  onChange(){
    this.sortBy.emit(this.value)
  }


}
