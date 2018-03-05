import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Weather} from "../../shared/models/weather";

@Injectable()
export class WeatherService {

    constructor(private _httpClient: HttpClient) {
    }

    getWeather(): Observable<Map<string, Weather[]>> {
        return this._httpClient.get<Map<string, Weather[]>>('/api/v1/weather')
    }

}