import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import {UserModalService} from './user-modal.service';
import {User, UserService} from '../../shared';
import {Observable} from "rxjs/Observable";
import {ApplicationState} from "../../store/appication-state";
import {select, Store} from "@ngrx/store";
import {UpdateUserAction} from "../../store/actions/actions";
import {Subscription} from "rxjs/Subscription";

@Component({
    selector: 'user-mgmt-dialog-form',
    templateUrl: './user-dialog-form.component.html'
})
export class UserMgmtDialogFormComponent implements OnDestroy {

    ngOnDestroy(): void {
        if (this.userSubscription) this.userSubscription.unsubscribe()
    }
    user$: Observable<User>;
    userSubscription: Subscription;
    isSaving: Boolean;

    constructor(public _activeModal: NgbActiveModal,
                private _router: Router,
                private _store: Store<ApplicationState>) {
        this.user$ = this._store.pipe(select(state => state.usersState.selectedUser))
    }


    clear() {
        this._router.navigate(["../"])
            .then(() => {
                this._router.navigate([".", {outlets: {popup: null}}])
                    .then(() => {
                        this._activeModal.dismiss('cancel');
                    });
            })
    }

    save() {
        this.isSaving = true;
        this.userSubscription = this.user$.subscribe(user => {
            this._router.navigate(["../"]).then(() => {
                this._router.navigate([".", {outlets: {popup: null}}]).then(() => {
                    this._store.dispatch(new UpdateUserAction(user));
                    this.isSaving = false;
                    this._activeModal.dismiss(user);

                })
            })
        })
    }
}

@Component({
    selector: 'user-dialog-form',
    template: ''
})
export class UserDialogFormComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(private route: ActivatedRoute,
                private userModalService: UserModalService) {
    }

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userModalService.open(UserMgmtDialogFormComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
