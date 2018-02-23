import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import {UserModalService} from './user-modal.service';
import {User} from '../../shared';
import {Observable} from "rxjs/Observable";
import {ApplicationState} from "../../store/appication-state";
import {select, Store} from "@ngrx/store";
import {Back, UpdateUserAction} from "../../store/actions/actions";
import {Subscription} from "rxjs/Subscription";
import {ToastsManager} from "ng2-toastr";

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

    constructor(public _activeModal: NgbActiveModal,
                private _router: Router,
                public toastr: ToastsManager,
                private _store: Store<ApplicationState>) {
        this.user$ = this._store.pipe(select(state => state.usersState.selectedUser))
    }


    clear() {
        this._store.dispatch(new Back());
        this._activeModal.dismiss('cancel');
    }

    save() {
        this.userSubscription = this.user$.subscribe(user => {
            this._router.navigate(["../"]).then(() => {
                this._store.dispatch(new UpdateUserAction(user));
                this._activeModal.dismiss(user);
                this.toastr.success(`User with ${user.id} was changed successfully!`, 'Success!');
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
