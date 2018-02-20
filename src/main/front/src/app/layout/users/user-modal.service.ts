import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {Component, Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {ApplicationState} from "../../store/appication-state";
import {Store} from "@ngrx/store";
import {GetEmptyUserAction, GetUserAction} from "../../store/actions/actions";

@Injectable()
export class UserModalService {
    private ngModalRef: NgbModalRef;


    constructor(private _modalService: NgbModal,
                private _router: Router,
                private _store: Store<ApplicationState>) {
        this.ngModalRef = null;
    }


    open(component: Component, id?: number): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngModalRef !== null;
            if (isOpen) {
                resolve(this.ngModalRef);
            }

            setTimeout(() => {
                this._store.dispatch(id ? new GetUserAction(id) : new GetEmptyUserAction());
                this.ngModalRef = this.userModalRef(component);
                resolve(this.ngModalRef);
            }, 0);
        });
    }

    userModalRef(component: Component): NgbModalRef {
        const modalRef = this._modalService.open(component, {size: 'lg', backdrop: 'static'});
        modalRef.result.then(() => {
            this._router.navigate([{outlets: {popup: null}}]).then(() => {
                this.ngModalRef = null;
            });
        }, (reason) => {
            this._router.navigate([{outlets: {popup: null}}]).then(() => {
                this.ngModalRef = null;
            });
        });
        return modalRef
    }
}