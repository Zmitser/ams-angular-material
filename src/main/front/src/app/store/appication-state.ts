import {User} from "../shared/models/user";

export interface ApplicationState {
    users: User[]
}

export const INITIAL_APPLICATION_STATE: ApplicationState = {users: []};