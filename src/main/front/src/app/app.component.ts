import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ToastsManager} from "ng2-toastr";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
    constructor(private _toastsManager: ToastsManager, private _viewContainerRef: ViewContainerRef) {
        this._toastsManager.setRootViewContainerRef(_viewContainerRef);

    }

    ngOnInit() {
    }
}
