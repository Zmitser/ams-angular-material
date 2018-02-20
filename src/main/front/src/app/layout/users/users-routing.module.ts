import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UsersComponent} from './users.component';
import {UserDeleteDialogComponent} from "./user-delete-dialog.component";
import {UserMgmtDetailsComponent} from "./user-details.component";
import {UserDialogFormComponent} from "./user-dialog-form.component";

const routes: Routes = [
    {
        path: '', component: UsersComponent
    },
    {
        path: ':id/details', component: UserMgmtDetailsComponent,
    },
    {
        path: ':id/delete', component: UserDeleteDialogComponent, outlet: 'popup'
    },
    {
        path: ':id/edit', component: UserDialogFormComponent, outlet: 'popup'
    },
    {
        path: 'new', component: UserDialogFormComponent, outlet: 'popup'
    },
];


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UsersRoutingModule {
}


