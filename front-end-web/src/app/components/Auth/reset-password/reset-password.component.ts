import { Component, OnInit } from '@angular/core';
import { Member } from 'src/app/model/Member';
import { Router } from '@angular/router';
import { PasswordValidation } from 'src/app/model/PasswordValidation';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})

export class ResetPasswordComponent implements OnInit {

  member?: Member;
  data?: PasswordValidation = {} as PasswordValidation;
  error?: string;

  constructor(private router:Router, private authService: AuthService) { 
    this.member = this.router.getCurrentNavigation().extras.state as Member;
    if(!this.member) {
      console.log(this.member);
      this.router.navigate(['/login']);
    }
  }

  ngOnInit(): void {
    
  }

  onSubmit() {
    if(this.data.password === this.data.confirmPassword) {
      if(this.validatePassword(this.data.password)) {
        this.member.password = this.data.password;
        this.authService.resetPassword(this.member).subscribe(
          () => {
            this.router.navigateByUrl('/login', { state : { message: "Password successfuly changed!", status: "success" } });
          },
          (err) => {
            this.error = err;
          }
        )
      }
    } else this.error = "Passorwds do not match.";
  }

  validatePassword(password: string): boolean {
    let regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    if(!regex.test(password)) {
      this.error = "Password must contain at least 1 lowercase character, 1 uppercase character, 1 numeric character, 1 special character (!@#$%^&*) and must be eight characters or longer.";
      return false;
    } 
    return true;
  }


}
