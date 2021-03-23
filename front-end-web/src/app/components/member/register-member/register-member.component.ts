import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Member } from 'src/app/model/Member';
import { MemberService } from 'src/app/services/member.service';

@Component({
  selector: 'app-register-member',
  templateUrl: './register-member.component.html',
  styleUrls: ['./register-member.component.scss']
})
export class RegisterMemberComponent implements OnInit {

  member: Member = {} as Member;
  error?: string;
  submitted: boolean = false;

  constructor(private memberService: MemberService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    // console.log(this.member);
    this.submitted = true;
    this.memberService.register(this.member)
    .subscribe(
      () => {
        this.router.navigate(['/member']);
      },
      (err) => {
        this.error = err;
      }
    );
  }
}
