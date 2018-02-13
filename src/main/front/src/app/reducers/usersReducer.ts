import {User} from '../shared/models/user';
import * as UserActions from '../actions/actions'

export type Action = UserActions.Actions

export function usersReducer(state: User[], action: Action) {
    switch (action.type) {
        case UserActions.LOAD_USERS_ACTION_SUCCESS:
            return action.payload;
        case UserActions.DELETE_USER_ACTION_SUCCESS:
            return handleDeleteSuccess(state, action);
        default:
            return state
    }

}

function handleDeleteSuccess(state, action) {
    let newState: User[] = state.slice();
    return newState.filter(user => user.id != action.payload)
}