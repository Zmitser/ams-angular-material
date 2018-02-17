import {User} from "../shared/models/user";

export interface UsersState {
    users: User[],
    selectedUser: User
}

export const INITIAL_USERS_STATE: UsersState = {users: [], selectedUser: null};