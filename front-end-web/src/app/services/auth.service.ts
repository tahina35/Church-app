import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { Member } from '../model/Member';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends BaseService {

  url: string = this.baseUrl + '/api/auth';

  constructor(private http: HttpClient, router: Router) {
    super();
  }

  findMember(username: string) {
    return this.http.get(this.url + '/forgot-password/' + username);
  }

  resetPassword(member: Member) {
    return this.http.post(this.url + '/reset-password', member);
  }
}
