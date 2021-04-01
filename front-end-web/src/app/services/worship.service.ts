import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { MorningService } from '../model/MorningService';
import { SundayService } from '../model/SundayService';
import { WednesdayService } from '../model/WednesdayService';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class WorshipService extends BaseService{

  sundayServiceUrl: string = this.baseUrl + '/api/worship/sunday-service';
  wednesdayServiceUrl: string = this.baseUrl + '/api/worship/wednesday-service';
  moringServiceUrl: string = this.baseUrl + '/api/worship/morning-service';

  constructor(private http: HttpClient) {
    super();
  }

  getPreachers() {
    return this.http.get(this.baseUrl + '/api/worship/preachers');
  }

  // Sunday service operations

  saveOrUpdateSundayService(service: SundayService) {
    return this.http.post(this.sundayServiceUrl, service);
  }

  getSundayServices(page: number) {
    return this.http.get(this.sundayServiceUrl + '/page/' + page);
  }

  deleteSundayService(date: Date, serviceType: number) {
    const formattedDate = formatDate(date, 'yyyy-MM-dd', 'en_US')
    return this.http.delete(this.sundayServiceUrl + '/delete?date=' + formattedDate + '&serviceType=' + serviceType);
  }

  // Wednesday service operations

  saveOrUpdateWednesdayService(service: WednesdayService) {
    return this.http.post(this.wednesdayServiceUrl, service);
  }

  getWednesdayServices(page: number) {
    return this.http.get(this.wednesdayServiceUrl + '/page/' + page);
  }

  deleteWednesdayService(date: Date) {
    const formattedDate = formatDate(date, 'yyyy-MM-dd', 'en_US')
    return this.http.delete(this.wednesdayServiceUrl + '/delete?date=' + formattedDate);
  }

  // Morning service operations

  saveOrUpdateMorningService(service: MorningService) {
    return this.http.post(this.moringServiceUrl, service);
  }

  getMorningServices(page: number) {
    return this.http.get(this.moringServiceUrl + '/page/' + page);
  }

  deleteMorningService(date: Date) {
    const formattedDate = formatDate(date, 'yyyy-MM-dd', 'en_US')
    return this.http.delete(this.moringServiceUrl + '/delete?date=' + formattedDate);
  }

  getServiceType(serviceType: number) :string {
    const type = environment.sundayServiceType;
    const val  = type.find(
        (element) => {
            return element.id == serviceType;
        }
    );
    return val.name;
  }

  getVideoId(url: string) :string{
    var regExp = /^.*(youtu\.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
    var match = url.match(regExp);
    console.log(match[2])
    return match[2];
  }

}
