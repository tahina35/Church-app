import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

export class BaseService {

  baseUrl: string = environment.apiBaseUrl;

  constructor() {}

  dateToString(date: NgbDate) {
    return date.year + '-' + date.month + '-' + date.day;
  }

}
