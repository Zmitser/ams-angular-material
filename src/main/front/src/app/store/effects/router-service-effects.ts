import {Injectable} from '@angular/core';
import {Location} from '@angular/common';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {map, tap} from 'rxjs/operators';
import {BACK, GO, Go} from "../actions/actions";
import {Router} from "@angular/router";

@Injectable()
export class RouterServiceEffects {

    @Effect({dispatch: false})
    navigate$ = this.actions$.pipe(
        ofType(GO),
        map((action: Go) => action.payload),
        tap(({path, query: queryParams, extras}) =>
            this.router.navigate(path, {queryParams, ...extras}))
    );

    @Effect({dispatch: false})
    navigateBack$ = this.actions$.pipe(
        ofType(BACK),
        tap(() => this.location.back())
    );


    constructor(private actions$: Actions,
                private router: Router,
                private location: Location) {
    }
}