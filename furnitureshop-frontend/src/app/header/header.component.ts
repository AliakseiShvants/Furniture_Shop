import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output()
  langChanged = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {
  }

  langSwitch(lang: string) {
    this.langChanged.emit(lang);
  }
}
