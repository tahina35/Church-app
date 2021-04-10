import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Notification } from '../model/Notification';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class NotificationService extends BaseService {

  constructor(private http: HttpClient) {
    super();
  }

  add(notification: Notification) {
    return this.http.post(this.baseUrl + '/api/notification', notification);
  }

  findLast30Days() {
    return this.http.get(this.baseUrl + '/api/notification');
  }

  delete(id: number) {
    return this.http.delete(this.baseUrl + '/api/notification/delete/' + id);
  }
}
