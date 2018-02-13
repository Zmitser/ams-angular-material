import {Action} from "@ngrx/store";
import {User} from "../shared/models/user";

export const LOAD_USERS_ACTION = 'LOAD_USERS_ACTION';
export const LOAD_USERS_ACTION_SUCCESS = "LOAD_USERS_ACTION_SUCCESS";
export const DELETE_USER_ACTION = "DELETE_USER_ACTION";
export const DELETE_USER_ACTION_SUCCESS = "DELETE_USER_ACTION_SUCCESS";

export class LoadUsersAction implements Action {
    readonly type: string = LOAD_USERS_ACTION;

}

export class LoadUsersActionSuccess implements Action {
    readonly type: string = LOAD_USERS_ACTION_SUCCESS;


    constructor(public payload: User[]) {
    }
}

export class DeleteUserActionAction implements Action {
    readonly type: string = DELETE_USER_ACTION;

    constructor(public payload: number) {
    }
}

export class DeleteUserActionSuccess implements Action {
    readonly type: string = DELETE_USER_ACTION_SUCCESS;


    constructor(public payload: number) {

    }
}

export type Actions =
    LoadUsersActionSuccess
// | DeleteUserActionSuccess