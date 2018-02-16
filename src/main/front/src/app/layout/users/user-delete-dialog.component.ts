import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import * as UserActions from "../../actions/actions";
import {User} from '../../shared';
import {UserModalService} from './user-modal.service';
import {ApplicationState} from "../../store/appication-state";
import {Store} from "@ngrx/store";

@Component({
    selector: 'user-mgmt-delete-dialog',
    templateUrl: './user-delete-dialog.component.html'
})
export class UserMgmtDeleteDialogComponent {

    user: User;

    constructor(private _store: Store<ApplicationState>,
                public _activeModal: NgbActiveModal,
                private _router: Router,) {
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

    confirmDelete(id: number) {
        this._router.navigate(["../"]).then(() => {
            this._router.navigate([".", {outlets: {popup: null}}]).then(() => {
                this._store.dispatch(new UserActions.DeleteUserActionAction(id));
                this._activeModal.dismiss(true);
            })
        })
    }
}

@Component({
    selector: 'user-delete-dialog',
    template: ''
})
export class UserDeleteDialogComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(private route: ActivatedRoute,
                private _userModalService: UserModalService) {
    }

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this._userModalService.open(UserMgmtDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}