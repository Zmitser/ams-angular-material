import {INITIAL_USERS_STATE, UsersState} from "./usersState";
import {INITIAL_ROUTER_REDUCER_STATE, RouterStateUrl} from "./router-state-url";
import {RouterReducerState} from "@ngrx/router-store";

export interface ApplicationState {
    usersState: UsersState,
    router: RouterReducerState<RouterStateUrl>;
}

export const INITIAL_APPLICATION_STATE: ApplicationState = {
    usersState: INITIAL_USERS_STATE,
    router: INITIAL_ROUTER_REDUCER_STATE
};