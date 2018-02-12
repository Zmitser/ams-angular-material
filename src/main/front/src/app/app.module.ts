import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthGuard, UserService} from './shared';
import {UserServiceEffects} from "./effects/user-service-effects";
import {EffectsModule} from "@ngrx/effects";
import {StoreModule} from "@ngrx/store";
import {reducers} from "./reducers/index";
import {INITIAL_APPLICATION_STATE} from "./store/appication-state";

export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: createTranslateLoader,
                deps: [HttpClient]
            }
        }),
        AppRoutingModule,
        StoreModule.forRoot(reducers, {
            initialState: INITIAL_APPLICATION_STATE
        }),
        EffectsModule.forRoot([
            UserServiceEffects
        ])
    ],
    declarations: [AppComponent],
    providers: [AuthGuard, UserService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
