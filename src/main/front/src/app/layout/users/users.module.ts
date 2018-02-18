import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersComponent} from "./users.component";
import {UsersRoutingModule} from "./users-routing.module";
import {UserDeleteDialogComponent} from "./user-delete-dialog.component";
import {UserModalService} from "./user-modal.service";
import {Ng2SmartTableModule} from "ng2-smart-table";
import {UserDetailsButtonRenderComponent} from "./user-components/user-details-button-render.component";
import {UserDeleteButtonRenderComponent} from "./user-components/user-delete-button-render.component";
import {UserMgmtDetailsComponent} from "./user-details.component";

@NgModule({
    imports: [
        CommonModule,
        UsersRoutingModule,
        Ng2SmartTableModule

    ],
    declarations: [
        UsersComponent,
        UserMgmtDetailsComponent,
        UserDeleteDialogComponent,
        UserDetailsButtonRenderComponent,
        UserDeleteButtonRenderComponent

    ],
    providers: [
        UserModalService
    ],
    entryComponents: [
        UserDetailsButtonRenderComponent,
        UserDeleteButtonRenderComponent
    ],
})
export class UsersModule {
}
