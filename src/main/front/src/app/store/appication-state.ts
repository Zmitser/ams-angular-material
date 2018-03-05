import {INITIAL_USERS_STATE, UsersState} from "./users-state";
import {INITIAL_ROUTER_REDUCER_STATE, RouterStateUrl} from "./router-state-url";
import {RouterReducerState} from "@ngrx/router-store";
import {INITIAL_WEATHER_STATE, WeatherState} from "./weather-state";

export interface ApplicationState {
    usersState: UsersState,
    weatherState: WeatherState
    router: RouterReducerState<RouterStateUrl>;
}

export const INITIAL_APPLICATION_STATE: ApplicationState = {
    usersState: INITIAL_USERS_STATE,
    weatherState: INITIAL_WEATHER_STATE,
    router: INITIAL_ROUTER_REDUCER_STATE
};