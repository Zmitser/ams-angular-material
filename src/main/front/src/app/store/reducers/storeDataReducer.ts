import {
    DELETE_USER_ACTION_SUCCESS,
    DeleteUserActionSuccess,
    GET_USER_ACTION_SUCCESS,
    GetUserActionSuccess,
    LOAD_USERS_ACTION_SUCCESS,
    LoadUsersActionSuccess,
    UPDATE_USER_ACTION_SUCCESS,
    UpdateUserActionSuccess
} from "../actions/actions";
import {UsersState} from "../usersState";


export function usersReducer(state: UsersState, action: any) {
    switch (action.type) {
        case LOAD_USERS_ACTION_SUCCESS:
            return handleLoadUsersActionSuccess(state, action);
        case DELETE_USER_ACTION_SUCCESS:
            return handleDeleteSuccess(state, action);
        case GET_USER_ACTION_SUCCESS:
            return handleGetUserActionSuccess(state, action);
        case UPDATE_USER_ACTION_SUCCESS:
            return handleSaveUserActionSuccess(state, action);
        default:
            return state
    }

}

function handleDeleteSuccess(state: UsersState, action: DeleteUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.users = newState.users.filter(user => user.id != action.payload);
    newState.selectedUser = null;
    newState.userTable.load(newState.users);
    return newState;

}

function handleLoadUsersActionSuccess(state: UsersState, action: LoadUsersActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.users = action.payload;
    newState.userTable.load(newState.users);
    return newState;
}

function handleGetUserActionSuccess(state: UsersState, action: GetUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.selectedUser = action.payload;
    return newState;
}

function handleSaveUserActionSuccess(state: UsersState, action: UpdateUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    let itemIndex = newState.users.findIndex(item => item.id == action.payload.id);
    newState.users[itemIndex] = action.payload;
    newState.selectedUser = null;
    newState.userTable.refresh();
    return newState
}



