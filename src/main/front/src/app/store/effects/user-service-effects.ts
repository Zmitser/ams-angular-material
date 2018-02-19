import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {UserService} from '../../shared/services/user.service';
import "rxjs/add/operator/switchMap";
import "rxjs/add/operator/map";
import {
    DELETE_USER_ACTION,
    DeleteUserActionAction,
    DeleteUserActionSuccess,
    GET_USER_ACTION,
    GetUserAction,
    GetUserActionSuccess,
    LOAD_USERS_ACTION,
    LoadUsersActionSuccess,
    UPDATE_USER_ACTION,
    UpdateUserAction,
    UpdateUserActionSuccess
} from "../actions/actions";
import {User} from "../../shared/models/user";

@Injectable()
export class UserServiceEffects {


    @Effect() findUsers$ = this._action$
        .ofType(LOAD_USERS_ACTION)
        .switchMap(action => this._userService.findAll())
        .map(data => new LoadUsersActionSuccess(data));

    @Effect() deleteUser$ = this._action$
        .ofType(DELETE_USER_ACTION)
        .map((action: DeleteUserActionAction) => action.payload)
        .switchMap((payload: number) => this._userService.delete(payload))
        .map((data: number) => new DeleteUserActionSuccess(data));

    @Effect() findUser$ = this._action$
        .ofType(GET_USER_ACTION)
        .map((action: GetUserAction) => action.payload)
        .switchMap((payload: number) => this._userService.findOne(payload))
        .map((data: User) => new GetUserActionSuccess(data));

    @Effect() saveUser$ = this._action$
        .ofType(UPDATE_USER_ACTION)
        .map((action: UpdateUserAction) => action.payload)
        .switchMap((user: User) => this._userService.save(user))
        .map((user: User) => new UpdateUserActionSuccess(user));


    constructor(private _action$: Actions, private _userService: UserService) {
    }
}