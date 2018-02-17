import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {User} from "../../shared/models/user";
import {select, Store} from "@ngrx/store";
import {ApplicationState} from "../../store/appication-state";
import {LoadUsersAction} from "../../actions/actions";
import {routerTransition} from "../../router.animations";

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css'],
    animations: [routerTransition()]
})
export class UsersComponent implements OnInit {

    users$: Observable<User[]>;

    constructor(private _store: Store<ApplicationState>) {
        this.users$ = this._store.pipe(select((state => state.usersState.users )));
    }

    ngOnInit() {
        this._store.dispatch(new LoadUsersAction())
    }
}
