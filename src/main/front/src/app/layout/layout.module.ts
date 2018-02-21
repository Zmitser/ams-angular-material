import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {LayoutRoutingModule} from './layout-routing.module';
import {LayoutComponent} from './layout.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {HeaderComponent} from './components/header/header.component';
import {UserMgmtDeleteDialogComponent} from "./users/user-delete-dialog.component";
import {UserMgmtDialogFormComponent} from "./users/user-dialog-form.component";
import {FormsModule} from "@angular/forms";

@NgModule({
    imports: [
        CommonModule,
        LayoutRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot(),

    ],
    declarations: [
        LayoutComponent,
        SidebarComponent,
        HeaderComponent,
        UserMgmtDeleteDialogComponent,
        UserMgmtDialogFormComponent
    ],
    entryComponents: [
        UserMgmtDeleteDialogComponent,
        UserMgmtDialogFormComponent
    ],


})
export class LayoutModule {
}
