import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import {UserModalService} from './user-modal.service';
import {User, UserService} from '../../shared';
import {Observable} from "rxjs/Observable";
import {ApplicationState} from "../../store/appication-state";
import {select, Store} from "@ngrx/store";
import {SaveUserAction} from "../../store/actions/actions";

@Component({
    selector: 'user-mgmt-dialog-form',
    templateUrl: './user-dialog-form.component.html'
})
export class UserMgmtDialogFormComponent {

    user$: Observable<User>;

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
        this.user$.subscribe(user => {
            this._router.navigate(["../"]).then(() => {
                this._router.navigate([".", {outlets: {popup: null}}]).then(() => {
                    this._store.dispatch(new SaveUserAction(user));
                    this._activeModal.dismiss(true);
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
