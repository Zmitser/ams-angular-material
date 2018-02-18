import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UsersComponent} from './users.component';
import {UserDeleteDialogComponent} from "./user-delete-dialog.component";
import {UserMgmtDetailsComponent} from "./user-details.component";

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
];


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UsersRoutingModule {
}


