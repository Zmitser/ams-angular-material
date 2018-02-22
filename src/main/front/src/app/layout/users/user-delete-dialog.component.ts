import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {User} from '../../shared';
import {UserModalService} from './user-modal.service';
import {ApplicationState} from "../../store/appication-state";
import {select, Store} from "@ngrx/store";
import {Observable} from "rxjs/Observable";
import {Back, DeleteUserActionAction} from "../../store";
import {ToastsManager} from "ng2-toastr";

@Component({
    selector: 'user-mgmt-delete-dialog',
    templateUrl: './user-delete-dialog.component.html'
})
export class UserMgmtDeleteDialogComponent {

    user$: Observable<User>;

    constructor(private _store: Store<ApplicationState>,
                private _activeModal: NgbActiveModal,
                public toastr: ToastsManager) {
        this.user$ = this._store.pipe(select(state => state.usersState.selectedUser))
    }

    clear() {
        this._store.dispatch(new Back());
        this._activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this._store.dispatch(new Back());
        this._store.dispatch(new DeleteUserActionAction(id));
        this._activeModal.dismiss(true);
        this.toastr.success(`User with ${id} was deleted successfully!`, 'Success!');
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