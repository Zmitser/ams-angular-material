import {ActionReducerMap} from "@ngrx/store";
import {ApplicationState} from "../appication-state";
import {usersReducer} from "./users-state-reducer";
import {routerReducer} from "@ngrx/router-store";
import {weatherReducer} from "./weather-state-reducer";


export const reducers: ActionReducerMap<ApplicationState> = {
    usersState: usersReducer,
    weatherState: weatherReducer,
    router: routerReducer
};