import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {Component, Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {UserService} from "../../shared/services/user.service";
import {User} from "../../shared/models/user";

@Injectable()
export class UserModalService {
    private ngModalRef: NgbModalRef;


    constructor(private _modalService: NgbModal,
                private _router: Router,
                private _userService: UserService) {
        this.ngModalRef = null;
    }


    open(component: Component, id?: number): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngModalRef !== null;
            if (isOpen) {
                resolve(this.ngModalRef);
            }
            if (id) {
                this._userService.findOne(id).subscribe((user) => {
                    this.ngModalRef = this.userModalRef(component, user);
                    resolve(this.ngModalRef);
                });
            } else {

                setTimeout(() => {
                    this.ngModalRef = this.userModalRef(component, new User());
                    resolve(this.ngModalRef);
                }, 0);
            }
        });
    }

    userModalRef(component: Component, user: User): NgbModalRef {
        const modalRef = this._modalService.open(component, {size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.user = user;
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