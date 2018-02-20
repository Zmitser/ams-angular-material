import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../models/user";
import "rxjs/add/observable/of";

@Injectable()
export class UserService {

    constructor(private _httpClient: HttpClient) {

    }

    findAll(): Observable<User[]> {
        return this._httpClient.get<User[]>('/api/v1/users')
    }

    delete(id: number): Observable<number> {
        return this._httpClient.delete<number>(`/api/v1/users/${id}`)
    }

    findOne(id: number): Observable<User> {
        return this._httpClient.get<User>(`/api/v1/users/${id}`)
    }

    createEmptyUser(): Observable<User> {
        return Observable.of(new User())
    }

    save(user: User): Observable<User> {
        console.log(`From save`, user);
        return this._httpClient.post<User>('/api/v1/users', user)
    }
}
