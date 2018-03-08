import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {WeatherComponent} from "./weather.component";
import {WeatherRoutingModule} from "./weather-routing.module";
import {KeysPipe} from "../../shared/pipes/key.pipe";
import {Ng2SmartTableModule} from "ng2-smart-table";

@NgModule({
    imports: [
        CommonModule,
        WeatherRoutingModule,
        Ng2SmartTableModule

    ],
    declarations: [
        WeatherComponent,
        KeysPipe
    ]
})
export class WeatherModule {
}