import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { LoginComponent } from './components/Auth/login/login.component';
import { PositionComponent } from './components/position/position.component';
import { UpdateMemberComponent } from './components/member/update-member/update-member.component';
import { RequestEmailComponent } from './components/Auth/request-email/request-email.component';
import { ResetPasswordComponent } from './components/Auth/reset-password/reset-password.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DisplayMemberComponent } from './components/member/display-member/display-member.component';
import { DisplayDepartmentComponent } from './components/department/display-department/display-department.component';
import { RegisterMemberComponent } from './components/member/register-member/register-member.component';
import { SundayServiceComponent } from './components/worshipServices/sunday-service/sunday-service.component';


import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgSelect2Module } from 'ng-select2';
import { NgbPaginationModule, NgbModalModule, NgbDatepickerModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthInterceptor } from './helper/auth.interceptor';
import { AuthGuard } from './helper/authGuard';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ServiceTypePipe } from './pipe/ServiceTypePipe';
import { WednesdayServiceComponent } from './components/worshipServices/wednesday-service/wednesday-service.component';
import { GenderPipe } from './pipe/GenderPipe';
import { MorningServiceComponent } from './components/worshipServices/morning-service/morning-service.component';
import { YouTubePlayerModule } from '@angular/youtube-player';
import { VideoPlayerModal } from './components/worshipServices/video-player.modal';


@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    LoginComponent,
    RequestEmailComponent,
    ResetPasswordComponent,
    RegisterMemberComponent,
    DashboardComponent,
    DisplayMemberComponent,
    DisplayDepartmentComponent,
    PositionComponent,
    UpdateMemberComponent,
    SundayServiceComponent,
    WednesdayServiceComponent,
    ServiceTypePipe,
    GenderPipe,
    MorningServiceComponent,
    VideoPlayerModal
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgSelect2Module,
    NgbPaginationModule,
    NgbModalModule,
    NgbDatepickerModule,
    NgbTooltipModule,
    NgbModule,
    YouTubePlayerModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthGuard
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
