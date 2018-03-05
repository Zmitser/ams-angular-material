import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {KeysPipe} from "./key.pipe";

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [KeysPipe]
})
export class SharedPipesModule {
}
