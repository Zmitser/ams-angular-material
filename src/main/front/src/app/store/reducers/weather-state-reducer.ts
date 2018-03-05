import {WeatherState} from "../weather-state";
import {LOAD_WEATHER_ACTION_SUCCESS, LoadWeatherActionSuccess} from "../actions/actions";

function handleLoadWeatherActionSuccess(state: WeatherState, action: LoadWeatherActionSuccess): WeatherState {
    const newState: WeatherState = Object.assign({}, state);
    newState.weather = action.payload;
    console.log("new State", newState.weather);
    return newState
}

export function weatherReducer(state: WeatherState, action: any): WeatherState {
    switch (action.type) {
        case LOAD_WEATHER_ACTION_SUCCESS:
            return handleLoadWeatherActionSuccess(state, action);
        default:
            return state
    }
}