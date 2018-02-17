import {INITIAL_USERS_STATE, UsersState} from "./usersState";

export interface ApplicationState {
    usersState: UsersState
}

export const INITIAL_APPLICATION_STATE: ApplicationState = {usersState: INITIAL_USERS_STATE};