import {
    DELETE_USER_ACTION_SUCCESS,
    DeleteUserActionSuccess,
    GET_EMPTY_USER_ACTION_SUCCESS,
    GET_USER_ACTION_SUCCESS,
    GetEmptyUserActionSuccess,
    GetUserActionSuccess,
    LOAD_USERS_ACTION_SUCCESS,
    LoadUsersActionSuccess,
    UPDATE_USER_ACTION_SUCCESS,
    UpdateUserActionSuccess
} from "../actions/actions";
import {UsersState} from "../users-state";
import {User} from "../../shared/models/user";


function handleEmptyGetUserActionSuccess(state: UsersState, action: GetEmptyUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.selectedUser = action.payload;
    return newState;
}

export function usersReducer(state: UsersState, action: any) {
    switch (action.type) {
        case LOAD_USERS_ACTION_SUCCESS:
            return handleLoadUsersActionSuccess(state, action);
        case DELETE_USER_ACTION_SUCCESS:
            return handleDeleteSuccess(state, action);
        case GET_USER_ACTION_SUCCESS:
            return handleGetUserActionSuccess(state, action);
        case GET_EMPTY_USER_ACTION_SUCCESS:
            return handleEmptyGetUserActionSuccess(state, action);
        case UPDATE_USER_ACTION_SUCCESS:
            return handleSaveUserActionSuccess(state, action);
        default:
            return state
    }

}

function handleDeleteSuccess(state: UsersState, action: DeleteUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.users.getAll().then((users: User[]) => {
        newState.selectedUser = null;
        newState.users.load(users.filter(user => user.id != action.payload));
    });
    return newState;

}

function handleLoadUsersActionSuccess(state: UsersState, action: LoadUsersActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.users.load(action.payload);
    return newState;
}

function handleGetUserActionSuccess(state: UsersState, action: GetUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.selectedUser = action.payload;
    return newState;
}

function handleSaveUserActionSuccess(state: UsersState, action: UpdateUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    const user: User = action.payload;
    newState.users.getAll().then((users: User[]) => {
        let itemIndex = users.findIndex(item => item.id == user.id);
        if (itemIndex !== -1) {
            users[itemIndex] = user;
        } else {
            users.push(user)
        }
        newState.selectedUser = null;
        newState.users.load(users)
    });

    return newState
}



