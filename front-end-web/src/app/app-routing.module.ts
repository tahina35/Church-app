import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './helper/authGuard';
import { LoginComponent } from './components/Auth/login/login.component';
import { MainComponent } from './components/main/main.component';
import { RequestEmailComponent } from './components/Auth/request-email/request-email.component';
import { ResetPasswordComponent } from './components/Auth/reset-password/reset-password.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RegisterMemberComponent } from './components/member/register-member/register-member.component';
import { DisplayMemberComponent } from './components/member/display-member/display-member.component';
import { DisplayDepartmentComponent } from './components/department/display-department/display-department.component';
import { PositionComponent } from './components/position/position.component';
import { UpdateMemberComponent } from './components/member/update-member/update-member.component';
import { SundayServiceComponent } from './components/worshipServices/sunday-service/sunday-service.component';
import { WednesdayServiceComponent } from './components/worshipServices/wednesday-service/wednesday-service.component';
import { MorningServiceComponent } from './components/worshipServices/morning-service/morning-service.component';

const routes: Routes = [
  { 
    path: '', component: MainComponent, canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'member/register', component: RegisterMemberComponent },
      { path: 'member', component: DisplayMemberComponent },
      { path: 'member/update/:id', component: UpdateMemberComponent },
      { path: 'department', component: DisplayDepartmentComponent },
      { path: 'position', component: PositionComponent },
      { path: 'sunday-service', component: SundayServiceComponent },
      { path: 'wednesday-service', component: WednesdayServiceComponent },
      { path: 'morning-service', component: MorningServiceComponent }
    ]
  }, 
  { path: 'login', component: LoginComponent }, 
  { path: 'forgot-password', component: RequestEmailComponent },
  { path: 'reset-password', component: ResetPasswordComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
