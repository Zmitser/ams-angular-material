import {Action} from "@ngrx/store";
import {User} from "../shared/models/user";

export const LOAD_USERS_ACTION = 'LOAD_USERS_ACTION';
export const LOAD_USERS_ACTION_SUCCESS = "LOAD_USERS_ACTION_SUCCESS";

export class LoadUsersAction implements Action {
    readonly type: string = LOAD_USERS_ACTION;

}

export class LoadUsersActionSuccess implements Action {
    readonly type: string = LOAD_USERS_ACTION_SUCCESS;


    constructor(public payload: User[]) {
    }
}

export type Actions =
    LoadUsersActionSuccess