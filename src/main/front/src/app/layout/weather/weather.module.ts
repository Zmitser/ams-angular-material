import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {WeatherComponent} from "./weather.component";
import {WeatherRoutingModule} from "./weather-routing.module";
import {KeysPipe} from "../../shared/pipes/key.pipe";

@NgModule({
    imports: [
        CommonModule,
        WeatherRoutingModule

    ],
    declarations: [
        WeatherComponent,
        KeysPipe
    ]
})
export class WeatherModule {
}