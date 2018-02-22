import {User} from "../shared/models/user";
import {LocalDataSource} from "ng2-smart-table";

export interface UsersState {
    selectedUser: User,
    users: LocalDataSource;
}

export const INITIAL_USERS_STATE: UsersState = {
    selectedUser: null,
    users: new LocalDataSource()
};