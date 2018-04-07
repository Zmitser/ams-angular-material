import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: 'auth', loadChildren: '../auth/auth.module#AuthModule'},
  {path: 'register', loadChildren: '../register/register.module#RegisterModule'},
  {path: 'login', loadChildren: '../pages/login/login.module#LoginModule'},
  // {path: 'editor', loadChildren: '../editor/editor.module#EditorModule'},

  {path: '**', redirectTo: 'auth/dashboard'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class LazyLoadModule {
}
