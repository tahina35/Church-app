import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  name: string;

  constructor(private loginService: LoginService, private router: Router) { 
    let member = JSON.parse(localStorage.getItem('member'));
    this.name = member.fname + ' ' + member.lname;
  }

  ngOnInit(): void {
    
  }

  logout() {
    this.loginService.logout();
  }

}
