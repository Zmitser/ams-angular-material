import {Weather} from "../shared";

export interface WeatherState {
    weather: Map<string, Weather[]>
}

export const INITIAL_WEATHER_STATE = {
    weather: new Map()
};

