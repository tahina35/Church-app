import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Router } from '@angular/router';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService, private router: Router ) { }

  private handleError(error: HttpErrorResponse){
    console.log(error);
    let message : string = "";
    if(error.error && error.error.code) {
        if(error.error.code == 500) {
            this.router.navigateByUrl('/login', { state : { message: error.error.message + ". Please log in!", status: "error" } });
        }
        message = error.error.message;
    } else {
        message = error.message;
    }
    return throwError(message);
}
  

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authReq = req;
    const token = this.cookieService.get('access-token');
    if (token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }

    return next.handle(authReq).pipe(
      catchError((errorResponse: HttpErrorResponse) => {
          return this.handleError(errorResponse);
      })
    );
  }
}
