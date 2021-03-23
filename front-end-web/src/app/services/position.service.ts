import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Position } from '../model/Position';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})

export class PositionService extends BaseService {

  constructor(private http: HttpClient, private router: Router) {
    super();
  }

  findAll() {
    return this.http.get(this.baseUrl + '/api/position');
  }

  findPaginated(page: number) {
    return this.http.get(this.baseUrl + '/api/position/page/' + page);
  }

  search(searchValue: string, page: number) {
    return this.http.get(this.baseUrl + '/api/position/search?v=' + searchValue + '&page=' + page );
  }

  add(position: Position) {
    return this.http.post(this.baseUrl + '/api/position', position);
  }

}
