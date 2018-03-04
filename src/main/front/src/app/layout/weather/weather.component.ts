import {Component, OnInit} from '@angular/core';
import {routerTransition} from "../../router.animations";

@Component({
    selector: 'app-weather',
    templateUrl: './weather.component.html',
    styleUrls: ['./weather.component.css'],
    animations: [routerTransition()]
})
export class WeatherComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
    }

}
