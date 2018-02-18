import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../shared/models/user";
import {ViewCell} from "ng2-smart-table";


@Component({
    template: `
        <button type="submit"
                [routerLink]="[{ outlets: { popup: [renderValue, 'edit'] } }]"
                replaceUrl="true"
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

    constructor() {
    }

    ngOnInit() {
        this.renderValue = +this.rowData.id;
    }
}