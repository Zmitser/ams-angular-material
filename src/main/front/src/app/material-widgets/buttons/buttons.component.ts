import {Component, OnInit} from '@angular/core';
import {BUTTON_HELPERS} from './helpers.data';

// import { fadeAnimation } from '../animation';
@Component({
  selector: 'cdk-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.scss'],
  // animations: [fadeAnimation]

})
export class ButtonsComponent implements OnInit {

  buttonHelpers: any = BUTTON_HELPERS;

  constructor() {
  }

  ngOnInit() {

  }

  public getRouterOutletState(outlet) {
    return outlet.isActivated ? outlet.activatedRoute : '';
  }

}
