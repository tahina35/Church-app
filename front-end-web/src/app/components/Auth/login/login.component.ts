import { Component, OnInit } from '@angular/core';
import { UsernameAndPassword } from '../../../model/UsernameAndPassword';
import { Router } from '@angular/router';
import { LoginService } from '../../../services/login.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  credentials: UsernameAndPassword = {} as UsernameAndPassword;
  error?: string;
  success?: string;

  constructor(private router: Router, private loginservice: LoginService) { 
    let message = this.router.getCurrentNavigation().extras.state;
    if(message) {
      if(message.status === "success") {
        this.success = message.message;
      } else {
        this.error = message.message;
      }  
    }
  }

  ngOnInit(): void {

  }

  onSubmit() {
    this.loginservice.login(this.credentials).subscribe(
      (res: HttpResponse<any>) => {
        let token = res.headers.get('Authorization');
        this.loginservice.storeToken(token);
        this.router.navigate(['/']);
      },
      (err) => {
        this.error = err;
      }
    );
  }

}
