import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersComponent} from "./users.component";
import {UsersRoutingModule} from "./users-routing.module";
import {UserDeleteDialogComponent} from "./user-delete-dialog.component";
import {UserModalService} from "./user-modal.service";

@NgModule({
    imports: [
        CommonModule,
        UsersRoutingModule

    ],
    declarations: [
        UsersComponent,
        UserDeleteDialogComponent
    ],
    providers: [
        UserModalService
    ]
})
export class UsersModule {
}
