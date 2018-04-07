import {NgModule} from '@angular/core';
import {ServicesComponent} from './services.component';
import {MatButtonModule, MatButtonToggleModule, MatCardModule, MatInputModule, MatListModule,} from '@angular/material';
import {CommonModule} from '@angular/common';
import {FlexLayoutModule} from '@angular/flex-layout';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: '', component: ServicesComponent},
];

@NgModule({
  imports: [
    MatCardModule,
    CommonModule,
    FlexLayoutModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatInputModule,
    MatListModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    // ServicesComponent,
  ],
  exports: [
    RouterModule
  ],
  providers: []
})
export class ServicesModule {
}
