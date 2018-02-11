import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


import {AppComponent} from './app.component';
import {UsersComponent} from './users/users.component';
import {UserService} from "./shared/services/user.service";
import {HttpClientModule} from "@angular/common/http";
import {StoreModule} from "@ngrx/store";
import {reducers} from "./reducers/index";
import {INITIAL_APPLICATION_STATE} from "./store/appication-state";
import {EffectsModule} from "@ngrx/effects";
import {UserServiceEffects} from "./effects/user-service-effects";


@NgModule({
    declarations: [
        AppComponent,
        UsersComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        NgbModule.forRoot(),
        StoreModule.forRoot(reducers, {
            initialState: INITIAL_APPLICATION_STATE
        }),
        EffectsModule.forRoot([
            UserServiceEffects
        ])
    ],
    providers: [UserService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
