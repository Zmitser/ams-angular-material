import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../shared/models/user";
import {ViewCell} from "ng2-smart-table";


@Component({
    template: `
        <button type="submit"
                [routerLink]="[{ outlets: { popup: [renderValue, 'delete'] } }]"
                replaceUrl="true"
                class="btn btn-danger btn-md">
            <span class="fa fa-remove"></span>
            <span class="d-none d-md-inline">Delete</span>
        </button>
    `,
})
export class DeleteButtonRenderComponent implements OnInit, ViewCell {
    renderValue: number;

    @Input() value: string | number;
    @Input() rowData: User;

    constructor() {
    }

    ngOnInit() {
        this.renderValue = +this.rowData.id;
    }
}