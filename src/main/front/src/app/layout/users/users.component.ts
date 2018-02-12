import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {User} from "../../shared/models/user";
import {UserService} from "../../shared/services/user.service";
import {select, Store} from "@ngrx/store";
import {ApplicationState} from "../../store/appication-state";
import * as UserActions from "../../actions/actions";
import {routerTransition} from "../../router.animations";

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css'],
    animations: [routerTransition()]
})
export class UsersComponent implements OnInit {

    users$: Observable<User[]>;

    constructor(private _userService: UserService, private _store: Store<ApplicationState>) {
        this.users$ = this._store.pipe(select('users'));
    }

    ngOnInit() {
        this._store.dispatch(new UserActions.LoadUsersAction())
    }

}
