import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../shared/models/user";
import {ViewCell} from "ng2-smart-table";
import {ApplicationState} from "../../../store/appication-state";
import {Store} from "@ngrx/store";
import {Go} from "../../../store/actions/actions";


@Component({
    template: `
        <button type="submit"
                (click)="showEditForm()"
                class="btn btn-primary btn-sm text-white">
            <span class="fa fa-pencil"></span>
            <span class="d-none d-md-inline">Edit</span>
        </button>
    `,
})
export class UserEditButtonRenderComponent implements OnInit, ViewCell {
    renderValue: number;

    @Input() value: string | number;
    @Input() rowData: User;

    constructor(private _store: Store<ApplicationState>) {
    }

    showEditForm() {
        this._store.dispatch(new Go({
            path: ['/users', {outlets: {popup: [this.renderValue, 'edit']}}],
            query: {},
            extras: {}
        }));
    }

    ngOnInit() {
        this.renderValue = +this.rowData.id;
    }
}