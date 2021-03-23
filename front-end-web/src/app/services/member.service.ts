import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { DeptMember } from '../model/DeptMember';
import { Member } from '../model/Member';
import { Role } from '../model/Role';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class MemberService extends BaseService {

  constructor(private http: HttpClient, private router: Router) {
    super();
  }

  findById(id: number) {
    return this.http.get(this.baseUrl + '/api/member/' + id);
  }

  getMemberDetails(id: number) {
    return this.http.get(this.baseUrl + '/api/member/details/' + id);
  }

  register(member: Member) {
    return this.http.post(this.baseUrl + '/api/registration', member);
  }

  findPaginated(page: number) {
    return this.http.get(this.baseUrl + '/api/member/page/' + page);
  }

  search(searchValue: string, page: number) {
    return this.http.get(this.baseUrl + '/api/member/search?v=' + searchValue + '&page=' + page );
  }

  addToDepartment(dept: DeptMember) {
    return this.http.post(this.baseUrl + '/api/member/add-to-department', dept);
  }

  assignRole(position: Role) {
    return this.http.post(this.baseUrl + '/api/member/assign-role', position);
  }

  getFiltersData() {
    return this.http.get(this.baseUrl + '/api/member/filters');
  }

}
