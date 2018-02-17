import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersComponent} from "./users.component";
import {UsersRoutingModule} from "./users-routing.module";
import {UserDeleteDialogComponent} from "./user-delete-dialog.component";
import {UserModalService} from "./user-modal.service";
import {Ng2SmartTableModule} from "ng2-smart-table";
import {DeleteButtonRenderComponent} from "./user-components/delete-button-render.component";

@NgModule({
    imports: [
        CommonModule,
        UsersRoutingModule,
        Ng2SmartTableModule

    ],
    declarations: [
        UsersComponent,
        UserDeleteDialogComponent,
        DeleteButtonRenderComponent

    ],
    providers: [
        UserModalService
    ],
    entryComponents: [
        DeleteButtonRenderComponent
    ],
})
export class UsersModule {
}
