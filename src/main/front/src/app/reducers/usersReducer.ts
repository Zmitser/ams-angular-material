import {User} from '../shared/models/user';
import * as UserActions from '../actions/actions'

export type Action = UserActions.Actions

export function usersReducer(state: User[], action: Action) {
    switch (action.type) {
        case UserActions.LOAD_USERS_ACTION_SUCCESS:
            return action.payload;
        default:
            return state
    }
}