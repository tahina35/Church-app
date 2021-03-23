import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Member } from 'src/app/model/Member';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-request-email',
  templateUrl: './request-email.component.html',
  styleUrls: ['./request-email.component.scss']
})
export class RequestEmailComponent implements OnInit {

  email?: string;
  error?: string;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.authService.findMember(this.email).subscribe(
      (member: Member) => {
        //console.log(member);
        this.router.navigateByUrl('/reset-password', { state : member});
      }, 
      (err) => {
        this.error = err;
      }
    )
  }

}
