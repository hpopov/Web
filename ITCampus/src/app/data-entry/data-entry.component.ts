import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { FormArray, FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';
import { PersonalInfoService } from '../personal-info/personal-info.service';


@Component({
  selector: 'app-data-entry',
  templateUrl: './data-entry.component.html',
  styleUrls: ['./data-entry.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class DataEntryComponent implements OnInit, OnDestroy {
  @Input() key: string;
  @Input() keyControl: FormControl = null;
  private keyControlSub: Subscription;
  @Input() keyDelim: string = ':';
  @Input() valueControl: FormControl = null;
  @Input() tupleValueControl: FormArray = null;
  @Input() delimiter: string = undefined;
  @Input() editMode: boolean = false;
  @Input() cancellable: boolean = false;
  // @Output('changed') changedEmitter: EventEmitter<DataEntry>;
  @Output('cancelled') cancelledEmitter: EventEmitter<void>;

  constructor(private personalInfoService: PersonalInfoService) {
    // this.changedEmitter = new EventEmitter<DataEntry>();
    this.cancelledEmitter = new EventEmitter<void>();
  }

  ngOnInit() {
    // this.personalInfoService.onUpdateEmitter.subscribe(()=>this.emitOnChanged());
    if (this.keyControl != null) {
      this.keyControlSub = this.keyControl.valueChanges.subscribe(value => this.key = value);
    }
  }

  ngOnDestroy(): void {
    if (this.keyControlSub != null) {
      this.keyControlSub.unsubscribe();
    }
  }

  // emitOnChanged() {
  //   let value: any = this.tupleValue===null? this.value : this.tupleValue;
  //   this.changedEmitter.emit({key: this.key.toLowerCase(), value: value});
  // }

}
