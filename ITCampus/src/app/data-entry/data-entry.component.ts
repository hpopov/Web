import { Component, OnInit, Input, Output, EventEmitter,
   ViewEncapsulation, OnChanges, SimpleChanges } from '@angular/core';
import { PersonalInfoService } from '../personal-info/personal-info.service';
import { DataEntry } from './data-entry.data';
import { Key } from 'protractor';


@Component({
  selector: 'app-data-entry',
  templateUrl: './data-entry.component.html',
  styleUrls: ['./data-entry.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class DataEntryComponent implements OnInit {

  @Input() key: string;
  @Input() keyDelim: string = ':';
  @Input() value: string = null;
  @Input() tupleValue: [any, any] = null;
  @Input() delimiter: string = undefined;
  @Input() editMode: boolean = false;
  @Input() editableKey: boolean = false;
  @Input() cancellable: boolean = false;
  @Output('changed') changedEmitter: EventEmitter<DataEntry>;
  @Output('cancelled') cancelledEmitter: EventEmitter<void>;

  constructor(private personalInfoService: PersonalInfoService) {
    this.changedEmitter = new EventEmitter<DataEntry>();
    this.cancelledEmitter = new EventEmitter<void>();
  }
  
  ngOnInit() {
    this.personalInfoService.onUpdateEmitter.subscribe(()=>this.emitOnChanged());
  }

  emitOnChanged() {
    let value: any = this.tupleValue===null? this.value : this.tupleValue;
    this.changedEmitter.emit({key: this.key.toLowerCase(), value: value});
  }

}
