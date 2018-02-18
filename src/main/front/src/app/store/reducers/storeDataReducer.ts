import {
    DELETE_USER_ACTION_SUCCESS,
    DeleteUserActionSuccess,
    GET_USER_ACTION_SUCCESS,
    GetUserActionSuccess,
    LOAD_USERS_ACTION_SUCCESS,
    LoadUsersActionSuccess,
    SAVE_USER_ACTION_SUCCESS,
    SaveUserActionSuccess
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
        case SAVE_USER_ACTION_SUCCESS:
            return handleSaveUserActionSuccess(state, action);
        default:
            return state
    }

}

function handleDeleteSuccess(state: UsersState, action: DeleteUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.users = newState.users.filter(user => user.id != action.payload);
    newState.selectedUser = null;
    return newState;

}

function handleLoadUsersActionSuccess(state: UsersState, action: LoadUsersActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.users = action.payload;
    return newState;
}

function handleGetUserActionSuccess(state: UsersState, action: GetUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.selectedUser = action.payload;
    return newState;
}

function handleSaveUserActionSuccess(state: UsersState, action: SaveUserActionSuccess) {
    const newState: UsersState = Object.assign({}, state);
    newState.users.push(action.payload);
    return newState
}



