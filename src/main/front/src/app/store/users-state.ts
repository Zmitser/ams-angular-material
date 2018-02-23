import {User} from "../shared/models/user";
import {ServerDataSource} from "ng2-smart-table";

export interface UsersState {
    selectedUser: User,
    users: ServerDataSource;
}

export const INITIAL_USERS_STATE: UsersState = {
    selectedUser: null,
    users: null
};