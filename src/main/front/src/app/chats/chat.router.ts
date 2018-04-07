import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ChatListComponent} from './chat-list/chat-list.component';

const materialWidgetRoutes: Routes = [
  {path: 'chat', component: ChatListComponent, data: {animation: 'chat'}},
];

@NgModule({
  imports: [
    RouterModule.forChild(materialWidgetRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ChatRouterModule {
}
