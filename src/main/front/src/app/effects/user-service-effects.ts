import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import * as UserActions from '../actions/actions'
import {UserService} from '../shared/services/user.service';
import "rxjs/add/operator/switchMap";
import "rxjs/add/operator/map";

@Injectable()
export class UserServiceEffects {


    @Effect() users$ = this._action$
        .ofType(UserActions.LOAD_USERS_ACTION)
        .switchMap(action => this._userService.findAll())
        .map(data => new UserActions.LoadUsersActionSuccess(data));


    constructor(private _action$: Actions, private _userService: UserService) {
    }
}