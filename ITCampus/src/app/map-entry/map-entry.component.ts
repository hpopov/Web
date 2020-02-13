import { Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { DataEntry } from '../data-entry/data-entry.data';
import { PersonalInfoService } from '../personal-info/personal-info.service';
import { MapEntry } from './map-entry.data';
import { FormArray, FormControl } from '@angular/forms';

@Component({
  selector: 'app-map-entry',
  templateUrl: './map-entry.component.html',
  styleUrls: ['./map-entry.component.scss', '../data-entry/data-entry.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class MapEntryComponent implements OnInit {

  @Input() key: string;
  @Input() keyDelim: string = ':';
  @Input() mapValueControls: FormArray;//should be array of tuples (also array)
  @Input() editMode: boolean = false;
  // @Output('changed') changedEmitter: EventEmitter<MapEntry>;
  @Output('length') lengthEmitter: EventEmitter<number>;
  // private map: [string, string][] = [];


  constructor(private personalInfoService: PersonalInfoService) {
    // this.changedEmitter = new EventEmitter<MapEntry>();
    this.lengthEmitter = new EventEmitter<number>();
  }

  ngOnInit() {
    // this.personalInfoService.onUpdateEmitter.subscribe(()=>this.emitOnChanged());
  }

  emitOnChanged() {
    // this.changedEmitter.emit({key: this.key, mapValue: this.map});
    // this.map = [];
  }
  convert(dataEntry: DataEntry): [string, string] {
    return [dataEntry.key, dataEntry.value.toString()];
  }

  onAddElement() {
    // this.mapValue.push(['','']);
    this.mapValueControls.push(new FormArray([new FormControl(null), new FormControl(null)]));
    this.lengthEmitter.emit(this.mapValueControls.length);
  }
}
