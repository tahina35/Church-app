import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Dept } from '../model/Dept';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService extends BaseService {

  constructor(private http: HttpClient) {
    super();
  }

  findPaginated(page: number) {
    return this.http.get(this.baseUrl + '/api/department/page/' + page);
  }

  search(searchValue: string, page: number) {
    return this.http.get(this.baseUrl + '/api/department/search?v=' + searchValue + '&page=' + page );
  }

  add(dept: Dept) {
    return this.http.post(this.baseUrl + '/api/department', dept);
  }

  findAll() {
    return this.http.get(this.baseUrl + '/api/department');
  }

  delete(id: number) {
    return this.http.delete(this.baseUrl + '/api/department/delete/' + id);
  }
}
