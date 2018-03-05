import {Component, OnInit} from '@angular/core';
import {routerTransition} from "../../router.animations";
import {ApplicationState} from "../../store/appication-state";
import {select, Store} from "@ngrx/store";
import {Observable} from "rxjs/Observable";
import {Weather} from "../../shared/models/weather";
import {LoadWeatherAction} from "../../store/actions/actions";

@Component({
    selector: 'app-weather',
    templateUrl: './weather.component.html',
    styleUrls: ['./weather.component.css'],
    animations: [routerTransition()]
})
export class WeatherComponent implements OnInit {

    weather$: Observable<Map<string, Weather[]>>;

    constructor(private _store: Store<ApplicationState>) {
        this.weather$ = this._store.pipe(select((state => state.weatherState.weather)));
    }

    ngOnInit() {
        this._store.dispatch(new LoadWeatherAction())
    }

}
