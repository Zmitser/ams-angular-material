import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../shared/models/user";
import {ViewCell} from "ng2-smart-table";


@Component({
    template: `
        <button type="submit"
                [routerLink]="[renderValue, 'details']"
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

    constructor() {
    }

    ngOnInit() {
        this.renderValue = +this.rowData.id;
    }
}