import {ActionReducerMap} from "@ngrx/store";
import {ApplicationState} from "../appication-state";
import {usersReducer} from "./storeDataReducer";
import {routerReducer} from "@ngrx/router-store";


export const reducers: ActionReducerMap<ApplicationState> = {
    usersState: usersReducer,
    router: routerReducer
};