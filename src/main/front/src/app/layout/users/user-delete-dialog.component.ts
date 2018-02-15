import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import {User, UserService} from '../../shared';
import {UserModalService} from './user-modal.service';

@Component({
    selector: 'user-mgmt-delete-dialog',
    templateUrl: './user-delete-dialog.component.html'
})
export class UserMgmtDeleteDialogComponent {

    user: User;

    constructor(private userService: UserService,
                public activeModal: NgbActiveModal,
                private _router: Router,) {
    }

    clear() {
        this._router.navigate(["../"])
            .then(() => {
                this._router.navigate([".", {outlets: {popup: null}}])
                    .then(() => {
                        this.activeModal.dismiss('cancel');
                    });
            })
    }

    confirmDelete(id: number) {
        this._router.navigate(["../"]).then(() => {
            this._router.navigate([".", {outlets: {popup: null}}]).then(() => {
                console.log(id);
                this.activeModal.dismiss(true);
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