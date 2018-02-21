import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';

import {User} from '../../shared';
import {Observable} from "rxjs/Observable";
import {ApplicationState} from "../../store/appication-state";
import {select, Store} from "@ngrx/store";
import {GetUserAction} from "../../store/actions/actions";
import {routerTransition} from "../../router.animations";

@Component({
    selector: 'user-mgmt-details',
    templateUrl: './user-details.component.html',
    animations: [routerTransition()]
})
export class UserMgmtDetailsComponent implements OnInit, OnDestroy {

    user$: Observable<User>;
    private subscription: Subscription;

    constructor(private _store: Store<ApplicationState>,
                private _route: ActivatedRoute) {

        this.user$ = this._store.pipe(select(state => state.usersState.selectedUser))
    }

    ngOnInit() {
        this.subscription = this._route.params.subscribe((params) => {
            this.load(params['id']);
        });
    }

    load(id) {
        this._store.dispatch(new GetUserAction(id))
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
