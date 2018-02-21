import {Params} from "@angular/router";
import {RouterReducerState} from "@ngrx/router-store";

export interface RouterStateUrl {
    url: string;
    params: Params;
    queryParams: Params;
}

export const INITIAL_ROUTER_REDUCER_STATE: RouterReducerState<RouterStateUrl> = {
    state: {
        url: window.location.pathname + window.location.search,
        params: [],
        queryParams: []
    },
    navigationId: 0
};