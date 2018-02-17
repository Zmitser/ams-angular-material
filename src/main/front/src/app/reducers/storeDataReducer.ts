import {
    ActionWithPayload,
    DELETE_USER_ACTION_SUCCESS,
    GET_USER_ACTION_SUCCESS,
    LOAD_USERS_ACTION_SUCCESS
} from "../actions/actions";
import {User} from "../shared/models/user";
import {UsersState} from "../store/usersState";


export function usersReducer(state: UsersState, action: any) {
    switch (action.type) {
        case LOAD_USERS_ACTION_SUCCESS:
            return handleLoadUsersActionSuccess(state, action);
        case DELETE_USER_ACTION_SUCCESS:
            return handleDeleteSuccess(state, action);
        case GET_USER_ACTION_SUCCESS:
            return handleGetUserActionSuccess(state, action);
        default:
            return state
    }

}

function handleDeleteSuccess(state: UsersState, action: ActionWithPayload<number>) {
    const newState: UsersState = Object.assign({}, state);
    newState.users = newState.users.filter(user => user.id != action.payload);
    newState.selectedUser = null;
    return newState;

}

function handleLoadUsersActionSuccess(state: UsersState, action: ActionWithPayload<User[]>) {
    const newState: UsersState = Object.assign({}, state);
    newState.users = action.payload;
    return newState;
}

function handleGetUserActionSuccess(state: UsersState, action: ActionWithPayload<User>) {
    const newState: UsersState = Object.assign({}, state);
    newState.selectedUser = action.payload;
    return newState;
}


