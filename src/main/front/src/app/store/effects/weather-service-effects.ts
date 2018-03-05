import {Injectable} from "@angular/core";
import {Actions, Effect} from "@ngrx/effects";
import {WeatherService} from "../../layout/weather/weather.service";
import {LOAD_WEATHER_ACTION, LoadWeatherActionSuccess} from "../actions/actions";
import "rxjs/add/operator/switchMap";
import "rxjs/add/operator/map";
import {Weather} from "../../shared/models/weather";

@Injectable()
export class WeatherServiceEffects {


    @Effect() getWeather$ = this._action$
        .ofType(LOAD_WEATHER_ACTION)
        .switchMap(() => this.weatherService.getWeather())
        .map((weather: Map<string, Weather[]>) => new LoadWeatherActionSuccess(weather));


    constructor(private _action$: Actions, private weatherService: WeatherService) {
    }
}