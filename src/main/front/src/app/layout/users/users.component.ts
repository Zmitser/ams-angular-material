import {Component, OnInit} from '@angular/core';
import * as moment from 'moment';
import {select, Store} from "@ngrx/store";
import {ApplicationState} from "../../store/appication-state";
import {routerTransition} from "../../router.animations";
import {Go, LoadUsersAction} from "../../store/actions/actions";
import {UserDeleteButtonRenderComponent} from "./user-components/user-delete-button-render.component";
import {UserDetailsButtonRenderComponent} from "./user-components/user-details-button-render.component";
import {UserEditButtonRenderComponent} from "./user-components/user-edit-button-render.component";
import {LocalDataSource} from "ng2-smart-table";

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css'],
    animations: [routerTransition()]
})
export class UsersComponent implements OnInit {
    source: LocalDataSource;
    settings = {
        actions: false,
        columns: {
            id: {
                title: 'ID'
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
            buttonDetails: {
                title: 'Details',
                type: 'custom',
                renderComponent: UserDetailsButtonRenderComponent,
                sort: false,
                edit: false,
                filter: false
            },
            buttonEdit: {
                title: 'Edit',
                type: 'custom',
                renderComponent: UserEditButtonRenderComponent,
                sort: false,
                edit: false,
                filter: false
            },
            buttonDelete: {
                title: 'Delete',
                type: 'custom',
                renderComponent: UserDeleteButtonRenderComponent,
                sort: false,
                edit: false,
                filter: false
            },

        },
        pager: {
            display: true,
            perPage: 9
        }
    };

    constructor(private _store: Store<ApplicationState>) {
        this._store.pipe(select((state => state.usersState.users ))).subscribe(users => {
            this.source = users
        });
    }

    ngOnInit() {
        this._store.dispatch(new LoadUsersAction())
    }

    showCreateForm() {
        this._store.dispatch(new Go({
            path: ['/users', {outlets: {popup: ['new']}}],
            query: {},
            extras: {}
        }));
    }
}
