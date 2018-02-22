import {RouterStateSerializer} from "@ngrx/router-store";
import {RouterStateUrl} from "./router-state-url";
import {RouterStateSnapshot} from "@angular/router";

export class CustomSerializer implements RouterStateSerializer<RouterStateUrl> {
    serialize(routerState: RouterStateSnapshot): RouterStateUrl {
        let route = routerState.root;
        const {url, root: {queryParams}} = routerState;
        const {params} = route;
        return {url, params, queryParams};
    }
}