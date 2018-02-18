import {Component, OnInit} from '@angular/core';
import * as moment from 'moment';
import {User} from "../../shared/models/user";
import {select, Store} from "@ngrx/store";
import {ApplicationState} from "../../store/appication-state";
import {routerTransition} from "../../router.animations";
import {LoadUsersAction} from "../../store/actions/actions";
import {UserDeleteButtonRenderComponent} from "./user-components/user-delete-button-render.component";
import {UserDetailsButtonRenderComponent} from "./user-components/user-details-button-render.component";

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css'],
    animations: [routerTransition()]
})
export class UsersComponent implements OnInit {
    users: User[];
    settings = {
        actions: false,
        columns: {
            id: {
                title: 'ID'
            },

            firstName: {
                title: 'First Name'
            },

            lastName: {
                title: 'Last Name'
            },

            userName: {
                title: 'Username'
            },

            email: {
                title: 'Email'
            },
            createDate: {
                title: 'Created',
                valuePrepareFunction: (value) => moment(value).format('YYYY-MM-DD')

            },
            buttonDelete: {
                title: 'Delete',
                type: 'custom',
                renderComponent: UserDeleteButtonRenderComponent,
                sort: false,
                edit: false,
                filter: false
            },
            buttonDetails: {
                title: 'Details',
                type: 'custom',
                renderComponent: UserDetailsButtonRenderComponent,
                sort: false,
                edit: false,
                filter: false
            },
        }
    };

    constructor(private _store: Store<ApplicationState>) {
        this._store.pipe(select((state => state.usersState.users ))).subscribe(users => {
            this.users = users
        });
    }

    ngOnInit() {
        this._store.dispatch(new LoadUsersAction())
    }
}
