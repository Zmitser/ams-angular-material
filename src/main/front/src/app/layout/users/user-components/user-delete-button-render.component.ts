import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../shared/models/user";
import {ViewCell} from "ng2-smart-table";
import {ApplicationState} from "../../../store/appication-state";
import {Store} from "@ngrx/store";
import {Go} from "../../../store";


@Component({
    template: `
        <button type="submit"
                (click)="showDeletePopup()"
                class="btn btn-danger btn-sm text-white">
            <span class="fa fa-remove"></span>
            <span class="d-none d-md-inline">Delete</span>
        </button>
    `,
})
export class UserDeleteButtonRenderComponent implements OnInit, ViewCell {
    renderValue: number;

    @Input() value: string | number;
    @Input() rowData: User;

    constructor(private _store: Store<ApplicationState>) {
    }

    showDeletePopup() {
        this._store.dispatch(new Go({
            path: ['/users', {outlets: {popup: [this.renderValue, 'delete']}}],
            query: {},
            extras: {}
        }));
    }

    ngOnInit() {
        this.renderValue = +this.rowData.id;
    }
}