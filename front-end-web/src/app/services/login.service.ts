import { Injectable } from '@angular/core';
import { UsernameAndPassword } from '../model/UsernameAndPassword';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { BaseService } from './base.service';
import { Member } from '../model/Member';

@Injectable({
  providedIn: 'root'
})

export class LoginService extends BaseService {

  constructor(private http: HttpClient, private cookieService: CookieService, private router: Router) {
    super();
  }

  login(credentials : UsernameAndPassword) {
    return this.http.post(this.baseUrl + '/login', credentials, { observe : "response"});
  }

  logout() {
    localStorage.removeItem('username');
    this.cookieService.delete('access-token');
    this.router.navigate(['/login']);
  }

  storeToken(token: string) {
    let re = /Bearer /gi; 
    let newToken = token.replace(re, ""); 
    this.cookieService.set( 'access-token', newToken);
  }
}
