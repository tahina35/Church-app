import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { News } from '../model/News';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class NewsService extends BaseService {

  constructor(private http: HttpClient) {
    super();
  }

  add(news: News) {
    return this.http.post(this.baseUrl + '/api/news', news);
  }

  findNews(timeRange: number) {
    return this.http.get(this.baseUrl + '/api/news?timeRange=' + timeRange);
  }

  delete(id: number) {
    return this.http.delete(this.baseUrl + '/api/news/delete/' + id);
  }
}
