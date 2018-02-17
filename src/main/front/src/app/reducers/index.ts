import {ActionReducerMap} from "@ngrx/store";
import {ApplicationState} from "../store/appication-state";
import {usersReducer} from "./storeDataReducer";


export const reducers: ActionReducerMap<ApplicationState> = {
    usersState: usersReducer,
};