import {Component, OnInit} from '@angular/core';
import {Links, LIST_HELPERS, Messages} from './helpers.data';

@Component({
  selector: 'cdk-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
  //animations: [fadeAnimation]
})
export class ListComponent implements OnInit {

  listHelpers: any = LIST_HELPERS;
  links = Links;

  showMultiListCode: boolean = false;
  messages = Messages;

  constructor() {
  }

  ngOnInit() {
  }


}
