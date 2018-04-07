import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'cdk-dashcard',
  templateUrl: './dashcard.component.html',
  styleUrls: ['./dashcard.component.scss']
})
export class DashcardComponent implements OnInit {

  @Input() dashData: any;

  constructor() {

  }

  ngOnInit() {
  }

}
