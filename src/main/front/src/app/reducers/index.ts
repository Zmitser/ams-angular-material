import {ActionReducerMap} from "@ngrx/store";
import {ApplicationState} from "../store/appication-state";
import {usersReducer} from "./usersReducer";

export const reducers: ActionReducerMap<ApplicationState> = {
    users: usersReducer
};