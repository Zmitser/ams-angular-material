import {Action} from "@ngrx/store";
import {User} from "../../shared/models/user";

export const LOAD_USERS_ACTION = 'LOAD_USERS_ACTION';
export const LOAD_USERS_ACTION_SUCCESS = "LOAD_USERS_ACTION_SUCCESS";
export const DELETE_USER_ACTION = "DELETE_USER_ACTION";
export const DELETE_USER_ACTION_SUCCESS = "DELETE_USER_ACTION_SUCCESS";
export const CANCEL_SELECTED_USER = "CANCEL_SELECTED_USER";
export const CANCEL_SELECTED_USER_SUCCESS = "CANCEL_SELECTED_USER_SUCCESS";
export const GET_USER_ACTION = "GET_USER_ACTION";
export const GET_USER_ACTION_SUCCESS = "GET_USER_ACTION_SUCCESS";
export const UPDATE_USER_ACTION = "UPDATE_USER_ACTION";
export const UPDATE_USER_ACTION_SUCCESS = "UPDATE_USER_ACTION_SUCCESS";


export interface ActionWithPayload<T> extends Action {
    payload: T;

}


export class LoadUsersAction implements Action {
    readonly type: string = LOAD_USERS_ACTION;

}

export class LoadUsersActionSuccess implements ActionWithPayload<User[]> {
    readonly type: string = LOAD_USERS_ACTION_SUCCESS;


    constructor(public payload: User[]) {
    }
}

export class DeleteUserActionAction implements ActionWithPayload<number> {
    readonly type: string = DELETE_USER_ACTION;

    constructor(public payload: number) {
    }
}

export class CancelSelectedUser implements ActionWithPayload<number> {
    readonly type: string = CANCEL_SELECTED_USER;

    constructor(public payload: number) {

    }
}

export class CancelSelectedUserSuccess implements ActionWithPayload<number> {
    readonly type: string = CANCEL_SELECTED_USER_SUCCESS;

    constructor(public payload: number) {

    }
}


export class DeleteUserActionSuccess implements ActionWithPayload<number> {
    readonly type: string = DELETE_USER_ACTION_SUCCESS;

    constructor(public payload: number) {

    }
}


export class GetUserAction implements ActionWithPayload<number> {
    readonly type: string = GET_USER_ACTION;

    constructor(public payload: number) {

    }
}

export class GetUserActionSuccess implements ActionWithPayload<User> {
    readonly type: string = GET_USER_ACTION_SUCCESS;

    constructor(public payload: User) {

    }
}

export class UpdateUserAction implements ActionWithPayload<User> {
    readonly type: string = UPDATE_USER_ACTION;

    constructor(public payload: User) {
    }
}

export class UpdateUserActionSuccess implements ActionWithPayload<User> {
    readonly type: string = UPDATE_USER_ACTION_SUCCESS;

    constructor(public payload: User) {
    }
}