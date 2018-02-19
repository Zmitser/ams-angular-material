import {User} from "../shared/models/user";
import {LocalDataSource} from "ng2-smart-table";

export interface UsersState {
    users: User[],
    selectedUser: User,
    userTable: LocalDataSource;
}

export const INITIAL_USERS_STATE: UsersState = {
    users: [],
    selectedUser: null,
    userTable: new LocalDataSource(this.users)
};