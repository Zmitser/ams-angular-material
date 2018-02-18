import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import {UserModalService} from './user-modal.service';
import {User, UserService} from '../../shared';
import {Observable} from "rxjs/Observable";
import {ApplicationState} from "../../store/appication-state";
import {select, Store} from "@ngrx/store";

@Component({
    selector: 'user-mgmt-dialog-form',
    templateUrl: './user-dialog-form.component.html'
})
export class UserMgmtDialogFormComponent implements OnInit {

    user$: Observable<User>;
    isSaving: Boolean;

    constructor(public _activeModal: NgbActiveModal,
                private _router: Router,
                private _store: Store<ApplicationState>) {
        this.user$ = this._store.pipe(select(state => state.usersState.selectedUser))
    }

    ngOnInit() {
        this.isSaving = false;
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
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        this._activeModal.dismiss(result.body);
    }

    private onSaveError() {
        this.isSaving = false;
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
            if (params['id']) {
                this.userModalService.open(UserMgmtDialogFormComponent as Component, params['id']);
            } else {
                this.userModalService.open(UserMgmtDialogFormComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
