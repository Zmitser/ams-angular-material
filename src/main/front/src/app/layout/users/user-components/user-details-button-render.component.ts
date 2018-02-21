import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../shared/models/user";
import {ViewCell} from "ng2-smart-table";
import {ApplicationState} from "../../../store/appication-state";
import {Store} from "@ngrx/store";
import {Go} from "../../../store/actions/actions";


@Component({
    template: `
        <button type="submit"
                (click)="showUserDetails()"
                class="btn btn-info btn-sm text-white">
            <span class="fa fa-eye"></span>
            <span class="d-none d-md-inline">View</span>
        </button>
    `,
})
export class UserDetailsButtonRenderComponent implements OnInit, ViewCell {
    renderValue: number;

    @Input() value: string | number;
    @Input() rowData: User;

    constructor(private _store: Store<ApplicationState>) {
    }

    showUserDetails() {
        this._store.dispatch(new Go({
            path: [this.renderValue, 'details'],
            query: {},
            extras: {}
        }));
    }

    ngOnInit() {
        this.renderValue = +this.rowData.id;
    }
}