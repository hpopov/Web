import { Component, OnInit, ViewEncapsulation, Input, 
  Output, EventEmitter, SimpleChanges } from '@angular/core';
import { PersonalInfoService } from '../personal-info/personal-info.service';
import { MapEntry } from './map-entry.data';
import { DataEntry } from '../data-entry/data-entry.data';

@Component({
  selector: 'app-map-entry',
  templateUrl: './map-entry.component.html',
  styleUrls: ['./map-entry.component.scss','../data-entry/data-entry.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class MapEntryComponent implements OnInit {

  @Input() key: string;
  @Input() keyDelim: string = ':';
  @Input() mapValue: [string, string][] = [];
  @Input() editMode: boolean = false;
  @Output('changed') changedEmitter: EventEmitter<MapEntry>;
  @Output('length') lengthEmitter: EventEmitter<number>;
  private map: [string, string][] = [];


  constructor(private personalInfoService: PersonalInfoService) {
    this.changedEmitter = new EventEmitter<MapEntry>();
    this.lengthEmitter = new EventEmitter<number>();
  }
  
  ngOnInit() {
    this.personalInfoService.onUpdateEmitter.subscribe(()=>this.emitOnChanged());
  }

  emitOnChanged() {
    this.changedEmitter.emit({key: this.key, mapValue: this.map});
    this.map = [];
  }
  convert(dataEntry: DataEntry): [string, string] {
    return [dataEntry.key, dataEntry.value.toString()];
  }

  onAddElement() {
    this.mapValue.push(['','']);
    this.lengthEmitter.emit(this.mapValue.length);
  }
}
