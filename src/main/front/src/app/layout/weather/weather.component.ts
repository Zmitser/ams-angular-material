import {Component, OnInit} from '@angular/core';
import {routerTransition} from "../../router.animations";
import {ApplicationState} from "../../store/appication-state";
import {select, Store} from "@ngrx/store";
import {Observable} from "rxjs/Observable";
import {Weather} from "../../shared/models/weather";
import {LoadWeatherAction} from "../../store/actions/actions";
import * as moment from 'moment';
import {ToastsManager} from "ng2-toastr";


@Component({
    selector: 'app-weather',
    templateUrl: './weather.component.html',
    styleUrls: ['./weather.component.css'],
    animations: [routerTransition()]
})
export class WeatherComponent implements OnInit {

    weather$: Observable<Map<string, Weather[]>>;

    options = {
        actions: false,
        columns: {
            low: {
                title: 'Low'
            },
            high: {
                title: 'High'
            },
            text: {
                title: 'Text'
            },
            date: {
                title: 'Date',
                valuePrepareFunction: (value) => moment(value).format('LLLL')
            },
            weatherServiceName: {
                title: 'Service Name'
            }
        }
    };

    constructor(private _store: Store<ApplicationState>, private toastr: ToastsManager) {
        this.weather$ = this._store.pipe(select((state => state.weatherState.weather)));

    }

    ngOnInit() {
        this._store.dispatch(new LoadWeatherAction());
        this.toastr.info('Wait some seconds and get weather', 'Welcome to the Weather Page!');
    }

}
