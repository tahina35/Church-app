import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Event } from '../model/Event';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})

export class EventService extends BaseService {

  constructor(private http: HttpClient) {
    super();
  }

  add(event: Event) {
    return this.http.post(this.baseUrl + '/api/event', event);
  }

  findEvents() {
    return this.http.get(this.baseUrl + '/api/event');
  }

  findById(id: string) {
    return this.http.get(this.baseUrl + '/api/event/' + id);
  }

  delete(id: number) {
    return this.http.delete(this.baseUrl + '/api/event/delete/' + id);
  }
}
