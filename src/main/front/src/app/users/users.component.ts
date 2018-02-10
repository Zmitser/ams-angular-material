import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {User} from "../shared/models/user";
import {UserService} from "../shared/services/user.service";

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

    users$: Observable<User[]>;

    constructor(private _userService: UserService) {

    }

    ngOnInit() {
        this.users$ = this._userService.findAll()
    }

}
